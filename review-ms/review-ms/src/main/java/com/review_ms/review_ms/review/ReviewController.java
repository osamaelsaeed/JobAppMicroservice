package com.review_ms.review_ms.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/company/{companyId}")
    public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable Long companyId){
        Review savedReview = reviewService.createReview(companyId, review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> retrieveAllReviews(){
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> retrieveReview(@PathVariable Long reviewId){
        Review review = reviewService.getReview(reviewId);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Review>> retrieveAllReviewsByCompany(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReviewsForCompany(companyId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/company/{companyId}/reviews/{reviewId}")
    public ResponseEntity<Review> retrieveReviewByCompanyAndId(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewByIdAndCompanyId(companyId, reviewId);
        return ResponseEntity.ok(review);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@RequestBody Review review, @PathVariable Long reviewId){
        Review updatedReview = reviewService.updateReview(review, reviewId);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Review> deleteReview( @PathVariable Long reviewId){
        Review deletedReview = reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(deletedReview, HttpStatus.OK);
    }


}
