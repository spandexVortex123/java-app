package com.loginexample.servicetest;

import com.loginexample.model.Student;
import com.loginexample.repository.StudentRepository;
import com.loginexample.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootTest
public class StudentTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void getAllStudents() {
        Mockito.when(studentRepository.findAll()).thenReturn(
                Stream.of(
                        new Student(1, "f", "l", "u", "p", null),
                        new Student(2, "fname", "lname", "uname", "passwd", null)
                ).collect(Collectors.toList())
        );

        Assertions.assertEquals(2, studentService.findAll().size());
    }

    @Test
    public void getStudentByUsername() {
        String username = "ipp";
        Mockito.when(studentRepository.findStudentByUsername(username)).thenReturn(
                new Student(123, "f", "l", "ipp", "asdf", null)
        );

        Student student = new Student(123, "f", "l", "ipp", "asdf", null);
        Assertions.assertEquals(student, studentService.findByUsername(username));
    }
}
