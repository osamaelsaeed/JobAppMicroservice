package com.job_ms.job_ms.job;

import com.job_ms.job_ms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    Job addJob(Job job);

    List<JobWithCompanyDTO> findAllJobs();

    Job findJobById(Long id);

    Job deleteJobById(Long id);

    Job updateJob(Long companyId, Job job);
}
