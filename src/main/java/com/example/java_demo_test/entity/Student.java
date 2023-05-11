package com.example.java_demo_test.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

  @Id
  @Column(name = "student_id")
  private String studentId; // 學生學號

  @Column(name = "name")
  private String name; // 姓名

  @Column(name = "credit")
  private int credit; //學分數


  public Student() {
  }

  public Student(String studentId, String name) {
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCredit() {
    return credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }


}
