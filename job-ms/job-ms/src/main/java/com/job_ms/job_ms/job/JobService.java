package com.job_ms.job_ms.job;

import java.util.List;

public interface JobService {
    Job addJob(Job job);

    List<Job> findAllJobs();

    Job findJobById(Long id);

    Job deleteJobById(Long id);

    Job updateJob(Long companyId, Job job);
}
