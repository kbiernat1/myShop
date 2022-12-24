package pl.kb.shop.category.model;

import lombok.Getter;
import pl.kb.shop.product.model.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String slug;
}
