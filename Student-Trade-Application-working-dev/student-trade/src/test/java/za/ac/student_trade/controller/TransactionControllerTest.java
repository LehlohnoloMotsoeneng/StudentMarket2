package za.ac.student_trade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.ac.student_trade.domain.Product;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        Product product = new Product();
        product.getProductId();
        product.getProductName();

        Student student = new Student();
        student.getStudentId();
        student.getFirstName();

        transaction = new Transaction.Builder()
                .setTransactionId(UUID.randomUUID().toString())
                .setImageOfProduct("image.jpg")
                .setProductLabel("Electronics")
                .setProductDescription("A nice laptop")
                .setProductCondition("New")
                .setTransactionDate(LocalDateTime.now())
                .setPrice(1200.00)
                .setProduct(product)
                .setBuyer(student)
                .build();

        repository.save(transaction);
    }

    @Test
    void testGetAllTransactions() throws Exception {
        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].transactionId").value(transaction.getTransactionId()));
    }

    @Test
    void testGetTransactionById() throws Exception {
        mockMvc.perform(get("/api/transactions/{id}", transaction.getTransactionId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(transaction.getTransactionId()));
    }

    @Test
    void testCreateTransaction() throws Exception {
        Transaction newTransaction = new Transaction.Builder()
                .setTransactionId(UUID.randomUUID().toString())
                .setImageOfProduct("newimage.jpg")
                .setProductLabel("Phone")
                .setProductDescription("Latest smartphone")
                .setProductCondition("Brand New")
                .setTransactionDate(LocalDateTime.now())
                .setPrice(800.00)
                .build();

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTransaction)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionId").value(newTransaction.getTransactionId()));
    }

    @Test
    void testUpdateTransaction() throws Exception {
        transaction = new Transaction.Builder()
                .copy(transaction)
                .setProductLabel("Updated Label")
                .build();

        mockMvc.perform(put("/api/transactions/{id}", transaction.getTransactionId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productLabel").value("Updated Label"));
    }

    @Test
    void testDeleteTransaction() throws Exception {
        mockMvc.perform(delete("/api/transactions/{id}", transaction.getTransactionId()))
                .andExpect(status().isNoContent());
    }
}
