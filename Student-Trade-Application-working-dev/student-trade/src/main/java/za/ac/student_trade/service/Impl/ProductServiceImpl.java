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
    private StudentRepository studentRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StudentRepository studentRepository) {
        this.productRepository = productRepository;
        this.studentRepository = studentRepository;
    }

//    @Override
//    public Product createProduct(Product product, Student sellerDetails) {
//        List<Student> seller = studentRepository.findByFirstNameAndLastName(sellerDetails.getFirstName(),sellerDetails.getLastName());
//
//
//        if (seller.isEmpty()) {
//            throw new RuntimeException("Seller not found with name: " +
//                    sellerDetails.getFirstName() + " " + sellerDetails.getLastName());
//        }
//
//        // Optional: Handle duplicates
//        if (seller.size() > 1) {
//            throw new RuntimeException("Multiple sellers found with name: " +
//                    sellerDetails.getFirstName() + " " + sellerDetails.getLastName());
//        }
//
//        Student currentSeller = seller.getFirst();
//
//        Product newProduct = ProductFactory.create(
//                product.getProductName(),
//                product.getProductDescription(),
//                product.getCondition(),
//                product.getPrice(),
//                product.getProductCategory(),
//                product.isAvailabilityStatus(),
//                product.getProductImageUrl(),
//                currentSeller
//        );
//
//        return productRepository.save(newProduct);
//    }

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
}
