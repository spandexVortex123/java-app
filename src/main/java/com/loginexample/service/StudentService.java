package com.loginexample.service;

import com.loginexample.model.Student;
import com.loginexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findByFirstName(String firstName) {
        return studentRepository.findStudentByFirstName(firstName);
    }

    public Student findByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }

    public Student deleteByUsername(String username) {
        Student student = studentRepository.findStudentByUsername(username);
        studentRepository.delete(student);
        return student;
    }

}
