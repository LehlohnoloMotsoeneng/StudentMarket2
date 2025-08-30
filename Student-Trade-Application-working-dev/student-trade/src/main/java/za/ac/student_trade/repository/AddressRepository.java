package za.ac.student_trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.student_trade.domain.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
