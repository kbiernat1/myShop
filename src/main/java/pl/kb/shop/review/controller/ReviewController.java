package pl.kb.shop.review.controller;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kb.shop.review.controller.dto.ReviewDto;
import pl.kb.shop.common.model.Review;
import pl.kb.shop.review.service.ReviewService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto) {
        return reviewService.addReview(Review.builder()
                .authorName(cleanContent(reviewDto.authorName()))
                .productId(reviewDto.productId())
                .content(cleanContent(reviewDto.content()))
                .build());
    }

    private String cleanContent(String text) {
        return Jsoup.clean(text, Safelist.none());
    }
}
