package ra.md4.service.ProductService;

import ra.md4.dto.product.CategoryDto;
import ra.md4.model.product.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    void save(CategoryDto categoryDto);
    Category findById(Integer id);
    Boolean isCategoryExists(String categoryName);
    void deleteById(Integer id);
}
