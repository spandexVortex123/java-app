package com.loginexample.StudentTest;

import com.loginexample.controller.StudentController;
import com.loginexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentController studentController;

    public void testGetAllStudents() {
        // Object result = studentController.getAllStudents();

    }
}
