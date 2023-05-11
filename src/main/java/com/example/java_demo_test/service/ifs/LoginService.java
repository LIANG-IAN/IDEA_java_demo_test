package com.example.java_demo_test.service.ifs;


import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.vo.LoginResponse;

public interface LoginService {
  LoginResponse register(Login user);
  LoginResponse active(String account,String password);
  LoginResponse login(String account,String password);
  LoginResponse searchUserByCity(String city);
}
