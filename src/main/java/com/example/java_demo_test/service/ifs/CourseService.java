package com.example.java_demo_test.service.ifs;


import com.example.java_demo_test.entity.Course;
import com.example.java_demo_test.entity.Student;
import com.example.java_demo_test.vo.CourseResponse;

import java.util.List;

public interface CourseService {


  public CourseResponse addCourse(Course course);

  public CourseResponse updateCourse(Course course);

  public CourseResponse deleteCourse(String courseCode);

  public CourseResponse selectCourse(List<String> courseCode, String studentId);

  public CourseResponse dropCourse(String courseCode, String studentId);


  public CourseResponse findCourseByCourseName(String courseName);

  public CourseResponse findCourseByCourseCode(String courseCode);

  public CourseResponse createStudent(String studentId,String studentName);

  public CourseResponse deleteStudent(String studentId);

  public CourseResponse getSelectedCoursesByStudentId(String studentId);


}
