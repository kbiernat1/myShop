package pl.kb.shop.admin.review;

import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
@Getter
public class AdminReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String authorName;
    private String content;
    private boolean moderated;
}