package ra.md4.service.ProductService.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.md4.dto.product.ProductDto;
import ra.md4.model.product.Product;
import ra.md4.repository.ProductRepositoty.ProductRepository;
import ra.md4.service.ProductService.IProductService;
import ra.md4.service.UploadService;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ModelMapper modelMapper; // can hoi cho nay

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(ProductDto productDto) {
        String productUrl = null;
        if (productDto.getProductId()!=null){
            productUrl = productRepository.findById(productDto.getProductId()).get().getProductUrl(); //can hoi dong nay
        }
        if (productDto.getProductFile() != null && productDto.getProductFile().getSize()>0){
            productUrl = uploadService.uploadFileToServer(productDto.getProductFile());
        }
        Product product = modelMapper.map(productDto, Product.class);
        product.setStatus(true);
        product.setProductUrl(productUrl);
        productRepository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    } // can hoi cho nay tai sao bat buoc tra ve null

    @Override
    public List<Product> findProductsByProductNameContaining(String name) {
        return productRepository.findProductsByProductNameContaining(name);
    }

    @Override
    public Boolean checkProductExists(String productName) {
        return productRepository.existsByProductName(productName);
    }

    @Override
    public List<Product> findProductByStatusIsTrue() {
        return productRepository.findProductByStatusIsTrue();
    }

    @Override
    public Page<Product> findAllPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
