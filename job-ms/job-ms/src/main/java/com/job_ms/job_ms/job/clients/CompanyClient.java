package com.job_ms.job_ms.job.clients;

import com.job_ms.job_ms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-MS")
public interface CompanyClient {

    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable Long id);

}
