package com.example.java_demo_test.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {

  @JsonProperty("account")
  private String account;

  @JsonProperty("pwd")
  private String pwd;

  @JsonProperty("verify_code")
  private int verifyCode;

  public RegisterRequest() {
  }

  public String getAccount() {
    return account;
  }

  public String getPwd() {
    return pwd;
  }

  public int getVerifyCode() {
    return verifyCode;
  }
}
