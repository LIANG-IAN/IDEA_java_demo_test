package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoResponse;

import java.util.List;

public interface PersonInfoService {

  PersonInfoResponse addPersonInfo(List<PersonInfo> personInfos);

  PersonInfoResponse getPersonInfo();

  PersonInfoResponse getPersonInfoById(String personInfoId);

  PersonInfoResponse findByAgeLessThanEqualOrderByAge(int age);

  PersonInfoResponse findTop3ByAgeBetweenOrderByAgeDesc(int num1,int num2);

  PersonInfoResponse findByCityContaining (String keyWorld);

  PersonInfoResponse findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int num1,String keyWorld);



}
