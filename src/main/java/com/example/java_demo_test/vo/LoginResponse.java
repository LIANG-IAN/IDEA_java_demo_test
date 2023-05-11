package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Login;

import java.util.List;

public class LoginResponse {
  private List<Login> loginList;
  private String message;

  public LoginResponse() {
  }

  public LoginResponse(String message) {
    this.message = message;
  }

  public LoginResponse(List<Login> loginList, String message) {
    this.loginList = loginList;
    this.message = message;
  }

  public List<Login> getLoginList() {
    return loginList;
  }

  public void setLoginList(List<Login> loginList) {
    this.loginList = loginList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
