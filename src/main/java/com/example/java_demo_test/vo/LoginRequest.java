package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Login;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LoginRequest {


  @JsonProperty("user_list")
  List<Login> userList;

  @JsonProperty("user")
  Login user;

  @JsonProperty("account_id")
  String accountId;

  @JsonProperty("password")
  String password;

  @JsonProperty("name")
  String name;

  @JsonProperty("age")
  int age;
  @JsonProperty("city")
  String city;

  public List<Login> getUserList() {
    return userList;
  }

  public Login getUser() {
    return user;
  }

  public String getAccount() {
    return accountId;
  }

  public String getPassword() {
    return password;
  }

  public String getCity() {
    return city;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
