package za.ac.student_trade;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.repository.AddressRepository;
import za.ac.student_trade.repository.AdministratorRepository;
import za.ac.student_trade.service.Impl.AdministratorServiceImpl;

@SpringBootApplication
public class StudentTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentTradeApplication.class, args);
	}


}
