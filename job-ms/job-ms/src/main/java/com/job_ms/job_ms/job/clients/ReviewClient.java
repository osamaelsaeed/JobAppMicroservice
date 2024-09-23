package com.job_ms.job_ms.job.clients;

import com.job_ms.job_ms.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "REVIEW-MS")
public interface ReviewClient {

    @GetMapping("/reviews/company/{companyId}")
    List<Review> getReviews(@PathVariable Long companyId);
}
