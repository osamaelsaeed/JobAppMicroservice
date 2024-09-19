package com.job_ms.job_ms.job.jobServiceImpl;


import com.job_ms.job_ms.job.Job;
import com.job_ms.job_ms.job.JobRepository;
import com.job_ms.job_ms.job.JobService;
import com.job_ms.job_ms.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public List<Job> findAllJobs() {
        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/api/public/company/1", Company.class);
        System.out.println(company + "Company Details");
        List<Job> jobs = jobRepository.findAll();
        return jobs;
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
