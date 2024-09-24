package com.review_ms.review_ms.review.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//the format with which message will be broadcast
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewMessage {
    private Long id;
    private String title;
    private String description;
    private double rating;
    private Long companyId;

}
