package pl.kb.shop.review.controller.dto;

import org.hibernate.validator.constraints.Length;

public record ReviewDto(@Length(min = 3, max = 60) String authorName, @Length(min = 3, max = 600) String content, Long productId) {
}
