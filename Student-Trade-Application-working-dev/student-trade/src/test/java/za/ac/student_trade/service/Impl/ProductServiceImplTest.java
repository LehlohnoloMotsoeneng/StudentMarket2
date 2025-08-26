package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.ProductFactory;
import za.ac.student_trade.repository.StudentRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private StudentRepository studentRepository;

    private Student seller;
    private Product product;



    @BeforeEach
    void setUp() {


        Student names = new Student.Builder().setFirstName("Asanda").setLastName("Ndhlela").build();

        seller = studentRepository.findByFirstNameAndLastName(names.getFirstName(),names.getLastName()).stream().findFirst().orElse(null);
//        product = ProductFactory.create("Macbook", "Bass Boosted, clear sound quality", "Good", 499.99,"Laptop",true,"image.png",seller);
    }

//    @Test
//    void a_create() {
//        Product newProduct = productService.createProduct(product, seller);
//        assertNotNull(newProduct);
//        System.out.println(newProduct.getProductId());
//    }

    @Test
    @Disabled
    void d_read() {
        Product existingProduct = productService.read(product.getProductId());
        assertNotNull(existingProduct);
        System.out.println(existingProduct.getProductId() + " " + existingProduct.getProductName());
    }

    @Test
    void c_update() {
        Product updatedProduct = new Product.Builder().copy(product).setProductName("JBL Speaker").setPrice(44.99).setProductCategory("Speaker").build();
        product = productService.update(updatedProduct);
        assertNotNull(product);
        System.out.println(product.getProductName());
    }
}