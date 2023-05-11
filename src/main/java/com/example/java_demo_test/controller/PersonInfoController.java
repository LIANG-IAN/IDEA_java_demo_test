package com.example.java_demo_test.controller;

import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoRequest;
import com.example.java_demo_test.vo.PersonInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonInfoController {
  @Autowired
  private PersonInfoService personInfoService;

  @PostMapping(value = "add_person_info")
  public PersonInfoResponse addPersonInfo(@RequestBody PersonInfoRequest personInfoRequest) {
    return personInfoService.addPersonInfo(personInfoRequest.getPersonInfoList());
  }

  @GetMapping(value = "get_person_info_by_id")
  public PersonInfoResponse getPersonInfoById(@RequestBody PersonInfoRequest personInfoRequest) {
    return personInfoService.getPersonInfoById(personInfoRequest.getPersonId());
  }

  @GetMapping(value = "get_person_info")
  public PersonInfoResponse getPersonInfo(@RequestBody PersonInfoRequest personInfoRequest) {
    return personInfoService.getPersonInfo();
  }

  @PostMapping(value = "get_person_info_by_age")
  public PersonInfoResponse getPersonInfoByAge(@RequestBody PersonInfoRequest personInfoRequest){
    return personInfoService.findByAgeLessThanEqualOrderByAge(personInfoRequest.getAge()) ;
  }


  @PostMapping(value = "get_three_person_info_by_age")
  public PersonInfoResponse getThreePersonInfoByAge(@RequestBody PersonInfoRequest personInfoRequest){
    return personInfoService.findTop3ByAgeBetweenOrderByAgeDesc(personInfoRequest.getNum1(),personInfoRequest.getNum2()) ;
  }


  @PostMapping(value = "get_person_info_by_city")
  public PersonInfoResponse findByCityContaining(@RequestBody PersonInfoRequest personInfoRequest){
    return personInfoService.findByCityContaining(personInfoRequest.getKeyWorld()) ;
  }


  @PostMapping(value = "get_person_info_by_age_and_city")
  public PersonInfoResponse findByAgeGreaterThanAndCityContainingOrderByAgeDesc(@RequestBody PersonInfoRequest personInfoRequest){
    return personInfoService.findByAgeGreaterThanAndCityContainingOrderByAgeDesc(personInfoRequest.getNum1(),personInfoRequest.getKeyWorld()) ;
  }




}




