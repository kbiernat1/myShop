package pl.kb.shop.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kb.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                new Product("Makrama 1", "Makramy", "Makrama w rozmiarze XL, kolor bordowy","/assets/bor.jpg",  new BigDecimal(299.00), "PLN"),
                new Product("Makrama 2", "Makramy", "Makrama w rozmiarze XL, kolor beżowy","/assets/bez.jpeg",  new BigDecimal(259.00), "PLN"),
                new Product("Makrama 3", "Makramy", "Makrama w rozmiarze XL, kolor czarny","/assets/cz.jpg",  new BigDecimal(199.00), "PLN")
        );
    }
}
