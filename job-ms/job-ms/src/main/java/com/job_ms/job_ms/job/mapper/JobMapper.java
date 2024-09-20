package com.job_ms.job_ms.job.mapper;

import com.job_ms.job_ms.job.Job;
import com.job_ms.job_ms.job.dto.JobWithCompanyDTO;
import com.job_ms.job_ms.job.external.Company;

public class JobMapper {

    public static JobWithCompanyDTO mapToJobWithCompanyDto(
            Job job,
            Company company
    ){
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJobId(job.getJobId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
