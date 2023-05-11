package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseDao extends JpaRepository<Course, String> {

  public List<Course> findCourseByCourseName(String courseName);


}

