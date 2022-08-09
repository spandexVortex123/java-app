package com.loginexample.controller;

import com.loginexample.model.Student;
import com.loginexample.repository.StudentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(path = "/all")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        System.out.println(student);
        studentRepository.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/search/{firstName}")
    public ResponseEntity<Student> findStudentByFirstName(@PathVariable String firstName) {
        Student student = studentRepository.findStudentByFirstName(firstName);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Student> deleteStudentByUsername(@RequestParam String username) {
        Student student = studentRepository.findStudentByUsername(username);
        if(student == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            studentRepository.delete(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Student> updateStudentByUsername(@NotNull @RequestBody Student student) {
        String username = student.getUsername();
        Student oldStudent = studentRepository.findStudentByUsername(username);
        if(oldStudent == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            // BeanUtils.copyProperties(student, oldStudent, "id", "username");

            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String password = student.getPassword();

            Integer result = studentRepository.updateStudentByUsername(username, firstName, lastName, password);

            if(result == 1) {
                return new ResponseEntity<Student>(student, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
    }
}
