package com.loginexample.controller;

import com.loginexample.dto.Response;
import com.loginexample.model.Student;
import com.loginexample.repository.StudentRepository;
import com.loginexample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/all")
    public List<Student> getAllStudents() {
        return studentService.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Response<Student>> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        Response<Student> studentResponse = Response.<Student>builder()
                .data(student)
                .message("Student Added successfully")
                .build();

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/search/{firstName}")
    public ResponseEntity<Response<List<Student>>> findStudentByFirstName(@PathVariable String firstName) {
        List<Student> studentList = studentService.findByFirstName(firstName);

        Response<List<Student>> studentResponse = Response.<List<Student>>builder()
                .data(studentList)
                .build();

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Response<Student>> deleteStudentByUsername(@RequestParam String username) {
        Student student = studentService.deleteByUsername(username);
        // some comment to test spring boot dev tools
        Response<Student> studentResponse = Response.<Student>builder()
                .data(student)
                .message("Student with username " + username + " deleted.")
                .build();

        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
