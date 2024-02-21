package ra.md4.service.ProductService.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md4.dto.product.CategoryDto;
import ra.md4.model.product.Category;
import ra.md4.service.ProductService.ICategoryService;
import ra.md4.repository.ProductRepositoty.CategoryRepositoty;
import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepositoty categoryRepositoty;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Category> findAll() {
        return categoryRepositoty.findAll();
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepositoty.save(category);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepositoty.findById(id).orElse(null);
    }

    @Override
    public Boolean isCategoryExists(String categoryName) {
        return categoryRepositoty.existsByCategoryName(categoryName);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepositoty.deleteById(id);
    }
}
