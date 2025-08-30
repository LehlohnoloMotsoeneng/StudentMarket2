package za.ac.student_trade.service;

import org.springframework.web.multipart.MultipartFile;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;

import java.io.IOException;
import java.util.List;

public interface IProductService extends IService<Product, Long>{
//    Product createProduct(Product product, Student seller);
    Product addProduct(Product product, MultipartFile imageFile) throws IOException;
    Product getProductById(Long id);
    void delete(Long id);
}
