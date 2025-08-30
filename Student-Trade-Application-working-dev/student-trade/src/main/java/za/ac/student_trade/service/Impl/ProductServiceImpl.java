package za.ac.student_trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.factory.ProductFactory;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.IProductService;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        return productRepository.save(
                ProductFactory.create(
                        product.getProductName(),
                        product.getProductDescription(),
                        product.getCondition(),
                        product.getPrice(),
                        product.getProductCategory(),
                        product.isAvailabilityStatus(),
                        imageFile.getOriginalFilename(),
                        imageFile.getContentType(),
                        imageFile.getBytes(),
                        product.getReleaseDate(),
                        product.getSeller()
                )
        );
    }

    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }


    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product read(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }

}
