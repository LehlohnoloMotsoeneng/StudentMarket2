package za.ac.student_trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.student_trade.domain.Administrator;
import za.ac.student_trade.domain.Student;
import za.ac.student_trade.service.Impl.AdministratorServiceImpl;
import za.ac.student_trade.service.Impl.StudentServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private StudentServiceImpl studentService;
    private AdministratorServiceImpl administratorServiceImpl;

    @Autowired
    public void setStudentService(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setAdministratorServiceImpl(AdministratorServiceImpl administratorServiceImpl) {
        this.administratorServiceImpl = administratorServiceImpl;
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @GetMapping("/read/{id}")
    public Student read(@PathVariable String id) {
        return this.studentService.read(id);
    }

    @PutMapping("/update")
    public Student update(@RequestBody Student student) {
        return this.studentService.update(student);
    }

    @GetMapping("/getAll")
    public List<Student> getAll() {
        return this.studentService.getAll();
    }
    // In StudentController
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            // Check Admin first
            List<Administrator> admins = administratorServiceImpl.findByEmailAndPassword(email.trim(), password.trim());
            if (!admins.isEmpty()) {
                Administrator admin = admins.get(0);

                Map<String, Object> successResponse = new HashMap<>();
                successResponse.put("success", true);
                successResponse.put("message", "Login successful");
                successResponse.put("role", "admin");

                Map<String, Object> adminData = new HashMap<>();
                adminData.put("adminId", admin.getAdminId());
                adminData.put("firstName", admin.getUsername());
                adminData.put("email", admin.getEmail());
                successResponse.put("data", adminData);

                return ResponseEntity.ok(successResponse);
            }

            // Check Student
            List<Student> students = studentService.findByEmailAndPassword(email.trim(), password.trim());
            if (!students.isEmpty()) {
                Student student = students.get(0);

                Map<String, Object> successResponse = new HashMap<>();
                successResponse.put("success", true);
                successResponse.put("message", "Login successful");
                successResponse.put("role", "student");

                Map<String, Object> studentData = new HashMap<>();
                studentData.put("studentId", student.getStudentId());
                studentData.put("firstName", student.getFirstName());
                studentData.put("lastName", student.getLastName());
                studentData.put("email", student.getEmail());
                successResponse.put("data", studentData);

                return ResponseEntity.ok(successResponse);
            }

            // Neither found
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
