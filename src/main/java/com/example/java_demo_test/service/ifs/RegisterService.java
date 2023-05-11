package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.vo.ActiveRequest;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

public interface RegisterService {

public RegisterResponse register(RegisterRequest request);

public RegisterResponse active(ActiveRequest request);

public RegisterResponse login(RegisterRequest request);

public RegisterResponse getRegTime(RegisterRequest request);

public RegisterResponse getRegTimeBySession(String account,String pwd);

public RegisterResponse getRegTimeBySession2(RegisterRequest request,String account,String pwd,Integer verifyCode);

}
