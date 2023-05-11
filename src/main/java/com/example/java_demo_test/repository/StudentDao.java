package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentDao extends JpaRepository<Student, String> {
}