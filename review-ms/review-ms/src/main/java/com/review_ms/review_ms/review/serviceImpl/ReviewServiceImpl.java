package com.review_ms.review_ms.review.serviceImpl;


import com.review_ms.review_ms.review.Review;
import com.review_ms.review_ms.review.ReviewRepository;
import com.review_ms.review_ms.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public Review createReview(Long companyId, Review review) {
        if (companyId != null && review != null){
        review.setCompanyId(companyId);
        Review savedReview = reviewRepository.save(review);
        return savedReview;
        }
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }

    @Override
    public List<Review> getAllReviewsForCompany(Long companyId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review updateReview( Review review, Long reviewId) {

            Review reviewDB = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new RuntimeException("Review not found with id" + reviewId));

            reviewDB.setTitle(review.getTitle());
            reviewDB.setDescription(review.getDescription());
            reviewDB.setRating(review.getRating());
            reviewDB.setCompanyId(review.getCompanyId());

            Review savedReview = reviewRepository.save(reviewDB);

        return savedReview;
    }

    @Override
    public Review getReviewByIdAndCompanyId(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);

        Review result = null;
        for (Review review : reviews){
            if (review.getReviewId() == reviewId){
                result = review;
            }
        }
        if (result == null){
            throw  new RuntimeException("No review with id" + reviewId);
        }

        return result;
    }

    @Override
    public Review deleteReview(Long reviewId) {
        Review reviewDB = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("review not found with id" + reviewId));
        reviewRepository.deleteById(reviewId);
        return reviewDB;
    }

    @Override
    public Review getReview(Long reviewId) {
        Review reviewDB = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("review not found with id" + reviewId));
        return reviewDB;
    }
}
