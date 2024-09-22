package com.job_ms.job_ms.job;

import com.job_ms.job_ms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    Job addJob(Job job);

    List<JobDTO> findAllJobs();

    JobDTO findJobById(Long id);

    Job deleteJobById(Long id);

    Job updateJob(Long companyId, Job job);
}
