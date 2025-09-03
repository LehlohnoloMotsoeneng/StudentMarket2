package za.ac.student_trade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.repository.ProductRepository;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Transaction transaction;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        //product.setProductId(UUID.randomUUID().toString());
        product = productRepository.save(product);

        transaction = new Transaction.Builder()
                .setTransactionId(UUID.randomUUID().toString())
                .setImageOfProduct("image.jpg")
                .setProductLabel("Electronics")
                .setProductDescription("A nice laptop")
                .setProductCondition("New")
                .setTransactionDate(LocalDateTime.now())
                .setPrice(1200.00)
                .setProduct(product)
                .build(); // no .setBuyer(...)

        transaction = transactionRepository.save(transaction);
    }

//    @Test
//    @Order(4)
//    void testGetAllTransactions() {
//        ResponseEntity<Transaction[]> response = restTemplate.getForEntity("/api/transactions", Transaction[].class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertTrue(response.getBody().length > 0);
//    }

    @Test
    @Order(2)
    void testGetTransactionById() {
        ResponseEntity<Transaction> response = restTemplate.getForEntity("/api/transactions/" + transaction.getTransactionId(), Transaction.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(transaction.getTransactionId(), response.getBody().getTransactionId());
    }

    @Test
    @Order(1)
    void testCreateTransaction() {
        Transaction newTransaction = new Transaction.Builder()
                .setTransactionId(UUID.randomUUID().toString())
                .setImageOfProduct("newimage.jpg")
                .setProductLabel("Phone")
                .setProductDescription("Latest smartphone")
                .setProductCondition("Brand New")
                .setTransactionDate(LocalDateTime.now())
                .setPrice(800.00)
                .setProduct(product)
                .build(); // no .setBuyer(...)

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transaction> request = new HttpEntity<>(newTransaction, headers);

        ResponseEntity<Transaction> response = restTemplate.postForEntity("/api/transactions", request, Transaction.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(newTransaction.getTransactionId(), response.getBody().getTransactionId());
        assertNull(response.getBody().getBuyer());
    }

    @Test
    @Order(3)
    void testUpdateTransaction() {
        transaction = new Transaction.Builder()
                .copy(transaction)
                .setProductLabel("Updated Label")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Transaction> request = new HttpEntity<>(transaction, headers);

        ResponseEntity<Transaction> response = restTemplate.exchange(
                "/api/transactions/" + transaction.getTransactionId(),
                HttpMethod.PUT,
                request,
                Transaction.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Label", response.getBody().getProductLabel());
    }

//    @Test
//    @Order(4)
//    void testDeleteTransaction() {
//        ResponseEntity<Void> response = restTemplate.exchange(
//                "/api/transactions/" + transaction.getTransactionId(),
//                HttpMethod.DELETE,
//                null,
//                Void.class
//        );
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//        assertFalse(transactionRepository.findById(transaction.getTransactionId()).isPresent());
//    }
}
