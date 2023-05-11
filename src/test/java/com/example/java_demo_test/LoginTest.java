package com.example.java_demo_test;

import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.repository.NewMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class LoginTest {

  @Autowired
  LoginDao loginDao;


}
