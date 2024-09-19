package com.job_ms.job_ms.job.dto;

import com.job_ms.job_ms.job.Job;
import com.job_ms.job_ms.job.external.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
