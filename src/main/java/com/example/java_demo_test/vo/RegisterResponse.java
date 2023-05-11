package com.example.java_demo_test.vo;

import java.time.LocalDateTime;

public class RegisterResponse {


  private String sessionId;

  private int verifyCode;

  private LocalDateTime regTime;

  private String message;

  public RegisterResponse() {
  }

  public RegisterResponse(String sessionId, int verifyCode, String message) {
    this.sessionId = sessionId;
    this.verifyCode = verifyCode;
    this.message = message;
  }

  public RegisterResponse(LocalDateTime regTime, String message) {
    this.regTime = regTime;
    this.message = message;
  }

  public RegisterResponse(String message) {
    this.message = message;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public int getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(int verifyCode) {
    this.verifyCode = verifyCode;
  }

  public LocalDateTime getRegTime() {
    return regTime;
  }

  public void setRegTime(LocalDateTime regTime) {
    this.regTime = regTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
