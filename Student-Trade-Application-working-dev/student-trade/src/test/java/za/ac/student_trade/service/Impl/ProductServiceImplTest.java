package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import za.ac.student_trade.domain.Address;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.AddressFactory;
import za.ac.student_trade.factory.ResidenceFactory;
import za.ac.student_trade.factory.StudentFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private ResidenceServiceImpl residenceService;

    private static Student seller;
    private static Product product;

    @BeforeAll
    static void setUp(@Autowired StudentServiceImpl studentService,
                      @Autowired ResidenceServiceImpl residenceService) {

        Address address = AddressFactory.createAddress(
                "456", "Service Street", "Test Area", "Cape Town", "Western Cape", 7000);

        Residence residence = ResidenceFactory.createResidence(
                "Service Residence", "S202", 2, "Block S", address);

        residence = residenceService.create(residence);

        seller = StudentFactory.createStudent(
                "Alice", "Smith", "alice.smith@test.com", "password456", residence);

        seller = studentService.create(seller);
    }

    @Test
    @Order(1)
    void addProduct() throws IOException {
        MockMultipartFile mockImage = new MockMultipartFile(
                "productImage", "macbook.jpg", "image/jpeg", "macbook image data".getBytes());

        Product newProduct = new Product.Builder()
                .setProductName("MacBook Pro")
                .setProductDescription("Latest MacBook Pro with M2 chip")
                .setCondition("New")
                .setPrice(2499.99)
                .setProductCategory("Laptop")
                .setAvailabilityStatus(true)
                .setSeller(seller)
                .build();

        Product created = productService.addProduct(newProduct, mockImage);
        assertNotNull(created);
        assertNotNull(created.getProductId());
        assertEquals("MacBook Pro", created.getProductName());
        assertEquals(seller.getStudentId(), created.getSeller().getStudentId());

        product = created;
        System.out.println("Created product: " + product);
    }

    @Test
    @Order(2)
    void addProductWithNullSeller() throws IOException {
        MockMultipartFile mockImage = new MockMultipartFile(
                "productImage", "macbook.jpg", "image/jpeg", "macbook image data".getBytes());

        Product newProduct = new Product.Builder()
                .setProductName("MacBook Pro")
                .setProductDescription("Latest MacBook Pro with M2 chip")
                .setCondition("New")
                .setPrice(2499.99)
                .setProductCategory("Laptop")
                .setAvailabilityStatus(true)
                .build();

        Product created = productService.addProduct(newProduct, mockImage);
        assertNotNull(created);
        assertNotNull(created.getProductId());
        assertEquals("MacBook Pro", created.getProductName());
        assertEquals(seller.getStudentId(), created.getSeller().getStudentId());

        product = created;
        System.out.println("Created product: " + product);
    }

    @Test
    @Order(2)
    @Disabled
    void read() {
        Product existingProduct = productService.read(product.getProductId());
        assertNotNull(existingProduct);
        assertEquals(product.getProductId(), existingProduct.getProductId());
        assertEquals("MacBook Pro", existingProduct.getProductName());
        // System.out.println("Read product: " + existingProduct);
    }

    @Test
    @Order(3)
    @Disabled
    void update() {
        Product updatedProduct = new Product.Builder()
                .copy(product)
                .setProductName("MacBook Pro Updated")
                .setPrice(2299.99)
                .build();

        Product result = productService.update(updatedProduct);
        assertNotNull(result);
        assertEquals("MacBook Pro Updated", result.getProductName());
        assertEquals(2299.99, result.getPrice());

        product = result;
        //  System.out.println("Updated product: " + product);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Product> allProducts = productService.getAll();
        assertNotNull(allProducts);
        assertFalse(allProducts.isEmpty());
        // System.out.println("Total products: " + allProducts.size());
    }

    @Test
    @Order(5)
    @Disabled
    void getProductById() {
        Product foundProduct = productService.getProductById(product.getProductId());
        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
        // System.out.println("Found product by ID: " + foundProduct);
    }
}