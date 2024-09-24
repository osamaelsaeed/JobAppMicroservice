package com.review_ms.review_ms.review.messaging;

import com.review_ms.review_ms.review.Review;
import com.review_ms.review_ms.review.dto.ReviewMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review){

        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(review.getReviewId());
        reviewMessage.setTitle(review.getTitle());
        reviewMessage.setDescription(review.getDescription());
        reviewMessage.setRating(review.getRating());
        reviewMessage.setCompanyId(review.getCompanyId());

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}
