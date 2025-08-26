package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductFactoryTest {

    private Student seller = new Student.Builder()
            .setFirstName("John")
            .setLastName("Doe")
            .setEmail("johndoe@gmail.com")
            .setPassword("johndoe")
            .build();

//    private Product product = ProductFactory.create("Laptop", "High-end gaming laptop", "New", 1500.00,
//            "Electronics", true, "imageUrlHere", seller
//    );

//    @Test
//    void createProduct() {
//        assertNotNull(product);
//        System.out.print(product);
//    }
}
