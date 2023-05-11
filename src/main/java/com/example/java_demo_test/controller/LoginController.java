package com.example.java_demo_test.controller;

import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginRequest;
import com.example.java_demo_test.vo.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
  @Autowired
  private LoginService loginService;

  //@PostMapping(value = "register")
  //public LoginResponse register(@RequestBody LoginRequest loginRequest) {
  //  return loginService.register(loginRequest.getUser());
  //}
  //
  //@PostMapping(value = "active")
  //public LoginResponse active(@RequestBody LoginRequest loginRequest) {
  //  return loginService.active(loginRequest.getAccount(), loginRequest.getPassword());
  //}
  //
  //@PostMapping(value = "login")
  //public LoginResponse login(@RequestBody LoginRequest loginRequest) {
  //  return loginService.login(loginRequest.getAccount(), loginRequest.getPassword());
  //}
  //
  //@PostMapping(value = "search_user_by_city")
  //public LoginResponse searchUserByCity(@RequestBody LoginRequest loginRequest) {
  //  return loginService.searchUserByCity(loginRequest.getCity());
  //}

}




