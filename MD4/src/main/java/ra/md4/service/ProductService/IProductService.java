package ra.md4.service.ProductService;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.md4.dto.product.ProductDto;
import ra.md4.model.product.Product;
import ra.md4.repository.ProductRepositoty.ProductRepository;
import java.util.List;
public interface IProductService {
    List<Product> findAll();
    void save(ProductDto productDto);
    Product findById(Integer id);
    List<Product>  findProductsByProductNameContaining(String name);
    Boolean checkProductExists(String productName);
    List<Product> findProductByStatusIsTrue();
    Page<Product> findAllPageable(Pageable pageable);
}
