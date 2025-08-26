package za.ac.student_trade.service.Impl;

import org.springframework.stereotype.Service;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.factory.StudentFactory;
import za.ac.student_trade.repository.StudentRepository;
import za.ac.student_trade.service.IStudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        Student createdStudent = StudentFactory.createStudent(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                student.getResidence()
        );
        return studentRepository.save(createdStudent);
    }

    @Override
    public Student read(String s) {
        return this.studentRepository.findById(s).orElse(null);
    }


    @Override
    public Student update(Student student) {
        return this.studentRepository.save(student);
    }

    @Override
    public List<Student> getAll(){
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> findByEmailAndPassword(String email, String password) {
        System.out.println("Searching for email: '" + email + "' and password: '" + password + "'");

        List<Student> result = this.studentRepository.findByEmailAndPassword(email, password);
        System.out.println("Found " + result.size() + " students");

        return result;
    }

    }

