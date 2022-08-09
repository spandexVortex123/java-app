package com.loginexample.repository;

import com.loginexample.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT * FROM student where first_name = :firstName", nativeQuery = true)
    Student findStudentByFirstName(String firstName);

    @Query(value = "SELECT student FROM Student student WHERE student.username = :username")
    Student findStudentByUsername(String username);

    @Query(value = "UPDATE Student s SET s.firstName = :firstName, s.lastName = :lastName, s.password = :password WHERE s.username = :username")
    @Modifying
    @Transactional
    Integer updateStudentByUsername(String username, String firstName, String lastName, String password);

}
