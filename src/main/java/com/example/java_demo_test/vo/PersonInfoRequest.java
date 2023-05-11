package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.PersonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonInfoRequest {


  @JsonProperty("person_info_list")
  List<PersonInfo> personInfoList;

  @JsonProperty("person_id")
  String personId;

  @JsonProperty("key_world")
  String keyWorld;

  @JsonProperty("age")
  int age;

  @JsonProperty("num1")
  int num1;

  @JsonProperty("num2")
  int num2;

  public void setPersonInfoList(List<PersonInfo> personInfoList) {
    this.personInfoList = personInfoList;
  }

  public List<PersonInfo> getPersonInfoList() {
    return personInfoList;
  }

  public String getPersonId() {
    return personId;
  }

  public int getAge() {
    return age;
  }

  public String getKeyWorld() {
    return keyWorld;
  }

  public int getNum1() {
    return num1;
  }

  public int getNum2() {
    return num2;
  }
}
