package com.job_ms.job_ms.job.jobServiceImpl;


import com.job_ms.job_ms.job.Job;
import com.job_ms.job_ms.job.JobRepository;
import com.job_ms.job_ms.job.JobService;
import com.job_ms.job_ms.job.clients.CompanyClient;
import com.job_ms.job_ms.job.clients.ReviewClient;
import com.job_ms.job_ms.job.dto.JobDTO;
import com.job_ms.job_ms.job.external.Company;
import com.job_ms.job_ms.job.external.Review;
import com.job_ms.job_ms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;

    int attempt = 0;


    @Override
    public Job addJob(Job job) {
        Job savedJob = jobRepository.save(job);
        return savedJob;
    }

    @Override
    //@CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    //@Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAllJobs() {
        System.out.println("At  tempt: "+ ++attempt);
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO convertToDTO(Job job){
            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
            JobDTO jobWithCompanyDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
        return jobWithCompanyDTO;
    }

    @Override
    public JobDTO findJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("No job with this id found"));
        JobDTO jobWithCompanyDTO = convertToDTO(job);
        return jobWithCompanyDTO;
    }

    @Override
    public Job deleteJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("No job with this id found"));
        jobRepository.deleteById(id);
        return job;
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job jobDB = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("No job with this id found"));

        jobDB.setDescription(job.getDescription());
        jobDB.setLocation(job.getLocation());
        jobDB.setTitle(job.getTitle());
        jobDB.setMinSalary(job.getMinSalary());
        jobDB.setMaxSalary(job.getMaxSalary());

        Job updatedJob = jobRepository.save(jobDB);

        return updatedJob;
    }
}
