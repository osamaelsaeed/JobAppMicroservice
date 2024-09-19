package com.review_ms.review_ms.review;

import java.util.List;

public interface ReviewService {
    Review createReview(Long companyId, Review review);

    List<Review> getAllReviews();

    List<Review> getAllReviewsForCompany(Long companyId);

    Review updateReview(Review review, Long reviewId);

    Review getReviewByIdAndCompanyId(Long companyId, Long reviewId);

    Review deleteReview(Long reviewId);

    Review getReview(Long reviewId);
}
