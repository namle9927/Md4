package ra.md4.repository.ProductRepositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md4.model.product.Category;

@Repository
public interface CategoryRepositoty extends JpaRepository<Category,Integer> {
    Boolean existsByCategoryName(String name);
}
