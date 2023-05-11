package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "person_info")
public class PersonInfo {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "city")
  private String city;


  public PersonInfo() {
  }

  public PersonInfo(String id, String name, int age, String city) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.city = city;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getAge() {
    return age;
  }


  public void setAge(int age) {
    this.age = age;
  }


  public String getCity() {
    return city;
  }


  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonInfo that = (PersonInfo) o;
    return Objects.equals(id, that.id);
  }
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

