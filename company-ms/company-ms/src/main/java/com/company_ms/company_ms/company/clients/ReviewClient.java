package com.company_ms.company_ms.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("REVIEW-MS")
public interface ReviewClient {

    @GetMapping("/reviews/averageRating/{companyId}")
    Double getAverageRating(@PathVariable Long companyId);
}
