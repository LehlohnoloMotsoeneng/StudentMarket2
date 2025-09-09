package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import za.ac.student_trade.domain.Administrator;

import java.util.List;
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    List<Administrator> findByEmailAndPassword(String password, String email);

}
