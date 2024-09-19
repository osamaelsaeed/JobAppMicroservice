package com.job_ms.job_ms.job.jobServiceImpl;


import com.job_ms.job_ms.job.Job;
import com.job_ms.job_ms.job.JobRepository;
import com.job_ms.job_ms.job.JobService;
import com.job_ms.job_ms.job.dto.JobWithCompanyDTO;
import com.job_ms.job_ms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;


    @Override
    public Job addJob(Job job) {
        Job savedJob = jobRepository.save(job);
        return savedJob;
    }

    @Override
    public List<JobWithCompanyDTO> findAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private JobWithCompanyDTO convertToDTO(Job job){
            RestTemplate restTemplate = new RestTemplate();
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.getForObject("http://localhost:8081/api/public/company/" + job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }

    @Override
    public Job findJobById(Long id) {

        Job job = jobRepository.findById(id).orElseThrow(() -> new RuntimeException("No job with this id found"));

        return job;
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
