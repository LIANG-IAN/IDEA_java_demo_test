package com.example.java_demo_test.service.impl;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.repository.RegisterDao;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.ActiveRequest;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class RegisterImpl implements RegisterService {

  @Autowired
  RegisterDao registerDao;

  @Override
  public RegisterResponse register(RegisterRequest request) {
    if (!StringUtils.hasText(request.getAccount()) || !StringUtils.hasText(request.getPwd())) {
      return new RegisterResponse("空!");
    }
    if (registerDao.existsById(request.getAccount())) {
      return new RegisterResponse("帳號已存在!");
    }
    Register register = new Register(request.getAccount(), request.getPwd());
    try {
      registerDao.save(register);

    } catch (Exception e) {
      return new RegisterResponse("錯誤!");
    }
    return new RegisterResponse("成功!");
  }

  @Override
  public RegisterResponse active(ActiveRequest request) {
    Register register = registerDao.findByAccountAndPwd(request.getAccount(), request.getPwd());
    if (register == null) {
      return new RegisterResponse("帳號找不到!");
    }
    if (register.isActive()) {
      return new RegisterResponse("成功!");
    }
    register.setActive(true);
    registerDao.save(register);
    return new RegisterResponse("成功!");
  }

  @Override
  public RegisterResponse login(RegisterRequest request) {
    if (!StringUtils.hasText(request.getAccount()) || !StringUtils.hasText(request.getPwd())) {
      return new RegisterResponse("空!");
    }
    Register register = registerDao.findByAccountAndPwdAndActive(request.getAccount(), request.getPwd(), true);
    if (register == null) {
      return new RegisterResponse("帳號錯誤或帳號會未啟動");
    }
    return new RegisterResponse("成功!");
  }

  @Override
  public RegisterResponse getRegTime(RegisterRequest request) {
    if (!StringUtils.hasText(request.getAccount()) || !StringUtils.hasText(request.getPwd())) {
      return new RegisterResponse("空!");
    }
    Register register = registerDao.findByAccountAndPwdAndActive(request.getAccount(), request.getPwd(), true);
    if (register == null) {
      return new RegisterResponse("帳號錯誤或帳號會啟動");
    }
    return new RegisterResponse(register.getRegTime(), "成功");
  }

  @Override
  public RegisterResponse getRegTimeBySession(String account, String pwd) {
    if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
      return new RegisterResponse("請先登錄!");
    }
    Register register = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
    if (register == null) {
      return new RegisterResponse("帳號錯誤或帳號會啟動");
    }
    return new RegisterResponse(register.getRegTime(), "成功");
  }

  public RegisterResponse getRegTimeBySession2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
    if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
      return new RegisterResponse("請先登錄!");
    }
    if (verifyCode == null || verifyCode != request.getVerifyCode()) {
      return new RegisterResponse("驗證碼不正確");
    }
    Register register = registerDao.findByAccountAndPwdAndActive(request.getAccount(), request.getPwd(), true);
    if (register == null) {
      return new RegisterResponse("帳號錯誤或帳號未啟動");
    }
    return new RegisterResponse(register.getRegTime(), "成功");
  }

  @Scheduled(cron = "0 * 14-16 * * *")
  public void scheduleTest() {
    System.out.println(LocalDateTime.now());
  }
}
