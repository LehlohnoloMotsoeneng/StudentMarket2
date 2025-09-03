package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.*;
import za.ac.student_trade.factory.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // allows non-static @BeforeAll
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceImplTest {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private ProductServiceImpl productService;

    private Transaction transaction;
    private Product product;
    private Student buyer, seller;

    @BeforeAll
    void setup() {

        Address sellerAddress = AddressFactory.createAddress(
                "10", "Seller Street", "Seller Suburb", "Cape Town", "Western Cape", 7001);
        Residence sellerResidence = ResidenceFactory.createResidence(
                "Seller Residence", "S101", 1, "Block S", sellerAddress);
        seller = StudentFactory.createStudent("Alice", "Seller", "seller@gmail.com", "pass123", sellerResidence);
        seller = studentService.create(seller);


        Address buyerAddress = AddressFactory.createAddress(
                "20", "Buyer Street", "Buyer Suburb", "Cape Town", "Western Cape", 7002);
        Residence buyerResidence = ResidenceFactory.createResidence(
                "Buyer Residence", "B102", 2, "Block B", buyerAddress);
        buyer = StudentFactory.createStudent("Bob", "Buyer", "buyer@gmail.com", "pass456", buyerResidence);
        buyer = studentService.create(buyer);


        product = ProductFactory.create(
                "P001",
                "Gaming Laptop",
                "High-performance gaming laptop with RTX graphics",
                2500.00,
                "Electronics",
                true,
                "Like New",
                "Cape Town",
                new byte[0],
                LocalDate.now(),
                seller
        );
        product = productService.create(product);


        transaction = TransactionFactory.createTransaction(
                LocalDateTime.now(),
                "laptop_transaction_image.jpg",
                "Gaming Laptop",
                "High-performance gaming laptop with RTX graphics",
                "Like New",
                2500.00,
                product,
                buyer
        );
    }

    @Test
    @Order(1)
    void createTransactionTest() {
        transaction = transactionService.create(transaction);
        assertNotNull(transaction, "Transaction should be created");
        System.out.println("Created Transaction ID: " + transaction.getTransactionId());
    }

    @Test
    @Order(2)
    void readTransactionTest() {
        Transaction read = transactionService.read(transaction.getTransactionId());
        assertNotNull(read, "Transaction should exist");
        assertEquals(buyer.getFirstName(), read.getBuyer().getFirstName());
        System.out.println("Buyer Name: " + read.getBuyer().getFirstName());
    }

    @Test
    @Order(3)
    void updateTransactionTest() {
        Transaction updated = new Transaction.Builder()
                .copy(transaction)
                .setProductLabel("Excellent")
                .build();

        Transaction result = transactionService.update(updated);
        assertEquals("Excellent", result.getProductLabel());
        System.out.println("Updated Product Label: " + result.getProductLabel());
    }

    @Test
    @Order(4)
    void getAllTransactionsTest() {
        List<Transaction> all = transactionService.getAll();
        assertTrue(all.size() > 0, "Should retrieve at least one transaction");
    }

//    @Test
//    @Order(5)
//    void deleteTransactionTest() {
//        transactionService.delete(transaction.getTransactionId());
//        Transaction deleted = transactionService.read(transaction.getTransactionId());
//        assertNull(deleted, "Transaction should be deleted");
//    }
}
