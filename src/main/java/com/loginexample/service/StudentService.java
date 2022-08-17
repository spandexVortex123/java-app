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

    public Student updateStudent(String username, Student student) {
        Student student1 = studentRepository.findStudentByUsername(username);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPassword(student.getPassword());
        studentRepository.save(student1);
        return student1;
    }

}
