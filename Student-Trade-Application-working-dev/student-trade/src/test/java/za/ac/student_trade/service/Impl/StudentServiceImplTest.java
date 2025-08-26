package za.ac.student_trade.service.Impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.student_trade.domain.Address;
import za.ac.student_trade.domain.Residence;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.AddressFactory;
import za.ac.student_trade.factory.ResidenceFactory;
import za.ac.student_trade.factory.StudentFactory;
import za.ac.student_trade.service.IStudentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentServiceImplTest {

    @Autowired
    private IStudentService service;
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    private ResidenceServiceImpl residenceServiceImpl;

    private static Residence residence;
    private static Address address;
    private static Student student;

    @BeforeAll
    static void setUp() {
        address = AddressFactory.createAddress("22","Brackeer", "Phola Park", "Piet Retief", "Mpumalanga",2380);

        residence = ResidenceFactory.createResidence("President House", "222", 2,"South Point", address);

        student = StudentFactory.createStudent("Asanda", "Ndhlela", "asandat@gmail.com","asanda123", residence);
    }

    @Test
    @Order(1)
    void create() {

        Residence savedResidence = residenceServiceImpl.create(residence);
        assertNotNull(savedResidence);
        System.out.println(savedResidence.toString());


        Student studentWithSavedResidence = new Student.Builder()
                .copy(student)
                .setResidence(savedResidence)
                .build();

        Student created = service.create(studentWithSavedResidence);
        assertNotNull(created);
        System.out.println(created.toString());


        student = created;
    }

    @Test
    @Order(2)
    void read() {
        Student read = service.read(student.getStudentId());
        assertNotNull(read);
        assertEquals(student.getStudentId(), read.getStudentId());

    }

    @Test
    @Order(3)
    void update() {
        Student updatedStudent = new Student.Builder()
                .copy(student)
                .setFirstName("Asanda Updated")
                .setEmail("asanda.updated@gmail.com")
                .build();

        Student updated = service.update(updatedStudent);
        assertNotNull(updated);
        assertEquals("Asanda Updated", updated.getFirstName());
        assertEquals("asanda.updated@gmail.com", updated.getEmail());

    }

    @Test
    @Order(4)
    void getAll() {
        List<Student> all = service.getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        System.out.println("Total students: " + all.size());
    }
}