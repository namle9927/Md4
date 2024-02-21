package ra.md4.repository.ProductRepositoty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.md4.model.product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Boolean existsByProductName(String name);
    List<Product> findProductByStatusIsTrue();
    Page<Product> findAll(Pageable pageable);
    List<Product>  findProductsByProductNameContaining(String name);
}
