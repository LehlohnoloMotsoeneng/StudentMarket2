package za.ac.student_trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Transaction;
import za.ac.student_trade.service.Impl.TransactionServiceImpl;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        try {
            Transaction transaction = transactionService.read(id);
            return ResponseEntity.ok(transaction);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction created = transactionService.create(transaction);
        return ResponseEntity.status(201).body(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable String id,
                                                         @RequestBody Transaction updatedTransaction) {
        Transaction existing = transactionService.read(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        Transaction saved = transactionService.update(updatedTransaction);
        return ResponseEntity.ok(saved);
    }


//   @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
//        transactionService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
