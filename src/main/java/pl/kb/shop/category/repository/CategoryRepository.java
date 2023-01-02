package pl.kb.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kb.shop.common.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findBySlug(String slug);
}
