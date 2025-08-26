package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.factory.AdministratorFactory;
import za.ac.student_trade.service.IAdministratorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdministratorServiceImplTest {

    @Autowired
    private IAdministratorService administratorService;

    private static Administrator admin;

    @BeforeAll
    static void beforeAll() {
        admin = AdministratorFactory.createAdministrator("hloni", "hloniyacho@gmail.com", "Password");
    }

    @Test
    @Order(1)
    void create() {
        administratorService.create(admin);
        System.out.println(admin);
    }

    @Test
    @Order(2)
    void read() {
        administratorService.read(admin.getAdminId());
    }

    @Test
    @Order(3)
    void update() {
        Administrator newAdmin = new Administrator.Builder().copy(admin).setUsername("hloni").build();
        administratorService.update(newAdmin);
    }

    @Test
    @Order(4)
    void getAll() {
        List<Administrator> all = administratorService.getAll();
    }
}