package com.job_ms.job_ms.job.mapper;

import com.job_ms.job_ms.job.Job;
import com.job_ms.job_ms.job.dto.JobDTO;
import com.job_ms.job_ms.job.external.Company;
import com.job_ms.job_ms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDto(
            Job job,
            Company company,
            List<Review> reviews
    ){
        JobDTO jobWithCompanyDTO = new JobDTO();
        jobWithCompanyDTO.setJobId(job.getJobId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);

        return jobWithCompanyDTO;
    }
}
