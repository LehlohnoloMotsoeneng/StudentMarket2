package za.ac.student_trade.factory;

import org.junit.jupiter.api.Test;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertNotNull;



class TransactionFactoryTest {

    @Test
    void createTransaction_success() {
        Product product = new Product.Builder()
                .setProductName("Laptop")
                .setProductDescription("Gaming laptop")
                .setCondition("New")
                .setPrice(1500.0)
                .setProductCategory("Electronics")
                .setAvailabilityStatus(true)
//                .setProductImageUrl("image.jpg")
                .build();

        Student buyer = new Student.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com")
                .build();

        LocalDateTime now = LocalDateTime.now();

        Transaction transaction = TransactionFactory.createTransaction(
                now,
                "imageOfProduct.jpg",
                "Laptop",
                "High-end laptop",
                "New",
                1500.00,
                product,
                buyer
        );


        assertNotNull(transaction);
        System.out.println(transaction);

    }
}
