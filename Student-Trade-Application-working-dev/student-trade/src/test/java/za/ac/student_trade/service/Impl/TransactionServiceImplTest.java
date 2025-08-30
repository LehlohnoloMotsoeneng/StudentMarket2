package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.*;
import za.ac.student_trade.factory.*;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Nested
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceImplTest {

        private static Transaction transaction;
        private static Product product;
        private static Student buyer, seller;

        @Autowired
        private TransactionServiceImpl transactionService;

        @Autowired
        private StudentServiceImpl studentService;

        @Autowired
        private ProductServiceImpl productService;

        @BeforeAll
        static void beforeAll(@Autowired StudentServiceImpl studentService,
                              @Autowired ProductServiceImpl productService) {

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

//            product = ProductFactory.create(
//                    "Gaming Laptop", "High-performance gaming laptop with RTX graphics",
//                    "Like New", 2500.00, "Electronics", true, "laptop_image.jpg", seller);
            product = productService.create(product);

            transaction = TransactionFactory.createTransaction(
                    LocalDateTime.now(),"laptop_transaction_image.jpg", "Gaming Laptop",
                    "High-performance gaming laptop with RTX graphics", "Like New",
                     2500.00, product, buyer);
        }

        @Test
        @Order(1)
        void create() {
            System.out.println(transaction);
            Transaction created = transactionService.create(transaction);
            System.out.println("here 2");
            assertNotNull(created);
            System.out.println("Created: " + transaction);
        }

        @Test
        @Order(2)
        void read() {
            Transaction read = transactionService.read(transaction.getTransactionId());
            assertNotNull(read);
            System.out.println("Transaction ID: " + read.getTransactionId());
            System.out.println("Buyer Name: " + read.getBuyer().getFirstName());


        }

        @Test
        @Order(3)
        void update() {
            Transaction updated = new Transaction.Builder()
                    .copy(transaction).setProductLabel("Excellent")
                    .build();
            Transaction result = transactionService.update(updated);


        }

        @Test
        @Order(4)
        void getAll() {
            List<Transaction> all = transactionService.getAll();

        }

    }

//}