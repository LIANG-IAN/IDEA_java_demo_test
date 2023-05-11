package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
public class Login {
  @Id
  @Column(name = "account_id")
  private String accountId;
  @Column(name = "password")
  private String password;
  @Column(name = "name")
  private String name;
  @Column(name = "age")
  private int age;
  @Column(name = "city")
  private String city;
  @Column(name = "register_time")
  private LocalDateTime regTime;
  @Column(name = "is_active")
  private boolean isActive;


  public Login() {
  }

  public Login(String accountId, String password, String name, int age, String city) {
    this.accountId = accountId;
    this.password = password;
    this.name = name;
    this.age = age;
    this.city = city;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public LocalDateTime getRegTime() {
    return regTime;
  }

  public void setRegTime(LocalDateTime regTime) {
    this.regTime = regTime;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public String toString() {
    return "Login{accountId = " + accountId + ", password = " + password + ", name = " + name + ", age = " + age + ", city = " + city + ", regTime = " + regTime + ", isActive = " + isActive + "}";
  }
}
