package za.ac.student_trade.service;

import za.ac.student_trade.domain.Administrator;

import java.util.List;

public interface IAdministratorService extends IService <Administrator, Long>{
    List<Administrator> findByEmailAndPassword(String email, String password);
}
