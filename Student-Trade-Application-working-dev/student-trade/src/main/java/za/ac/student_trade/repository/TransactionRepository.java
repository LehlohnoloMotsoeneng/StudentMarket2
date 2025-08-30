package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.student_trade.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
