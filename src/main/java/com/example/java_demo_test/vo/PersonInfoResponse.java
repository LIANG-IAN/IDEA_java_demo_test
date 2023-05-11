package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.PersonInfo;

import java.util.List;

public class PersonInfoResponse {
  private String message;
  private PersonInfo onePersonInfo;
  private List<PersonInfo> personInfoResponseList;

  public PersonInfoResponse() {

  }

  public PersonInfoResponse(String message) {
  }

  public PersonInfoResponse(PersonInfo onePersonInfo, String message) {
    this.message = message;
    this.onePersonInfo = onePersonInfo;
  }

  public PersonInfoResponse(List<PersonInfo> personInfoResponseList, String message) {
    this.message = message;
    this.personInfoResponseList = personInfoResponseList;
  }

  public String getMessage() {
    return message;
  }

  public PersonInfo getOnePersonInfo() {
    return onePersonInfo;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<PersonInfo> getPersonInfoResponseList() {
    return personInfoResponseList;
  }

  public void setPersonInfoResponseList(List<PersonInfo> personInfoResponseList) {
    this.personInfoResponseList = personInfoResponseList;
  }
}



