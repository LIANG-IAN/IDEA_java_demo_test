package com.example.java_demo_test.service.impl;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.repository.LoginDao;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginImpl implements LoginService {

  @Autowired
  LoginDao loginDao;

  @Override
  public LoginResponse register(Login user) {
    if (isUserRegular(user)) {
      return new LoginResponse("輸入錯誤");
    }
    if (loginDao.existsById(user.getAccountId())) {
      return new LoginResponse("帳號已存在");
    }
    List<Login> userList = new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();
    user.setRegTime(now);
    user.setIsActive(false);
    userList.add(user);
    loginDao.saveAll(userList);
    return new LoginResponse("帳號創建成功");
  }

  @Override
  public LoginResponse active(String account, String password) {
    if (isAccountRegular(account) || isPasswordRegular(password)) {
      return new LoginResponse("輸入錯誤");
    }
    Optional<Login> optionalLogin = loginDao.findById(account);
    if (!optionalLogin.isPresent()) {
      return new LoginResponse("帳號不存在");
    }
    Login user = optionalLogin.get();
    if (user.isActive()) {
      return new LoginResponse("帳號已啟動成功，請直接登錄");
    }
    user.setIsActive(true);
    loginDao.save(user);
    return new LoginResponse("帳號啟動成功");
  }

  @Override
  public LoginResponse login(String account, String password) {
    if (isAccountRegular(account) || isPasswordRegular(password)) {
      return new LoginResponse("輸入錯誤");
    }
    Optional<Login> optionalLogin = loginDao.findById(account);
    if (!optionalLogin.isPresent()) {
      return new LoginResponse("帳號不存在");
    }
    Login user = optionalLogin.get();
    if (!user.getPassword().equals(password)) {
      return new LoginResponse("帳號密碼錯誤");
    }
    if (!user.isActive()) {
      return new LoginResponse("帳號未啟動過，請先啟動");
    }
    return new LoginResponse("登錄成功");
  }

  @Override
  public LoginResponse searchUserByCity(String city) {
    if (!StringUtils.hasText(city)) {
      return new LoginResponse("城市名輸入錯誤");
    }
    List<Login> loginList = loginDao.findByCityLikeOrderByAge(city+"%");
    if (loginList.isEmpty()) {
      return new LoginResponse("沒有相符的使用者");
    }
    List<Login> userNotIncludePassword = new ArrayList<>();
    for (Login login : loginList) {
      login.setPassword("*********");
      userNotIncludePassword.add(login);
    }
    return new LoginResponse(userNotIncludePassword,"尋找到"+userNotIncludePassword.size()+"筆資料");
  }

  public boolean isUserRegular(Login user) {
    return user == null
            || !accountIdPattern(user.getAccountId())
            || !passwordPattern(user.getPassword())
            || !StringUtils.hasText(user.getName())
            || !StringUtils.hasText(user.getCity())
            || user.getAge() < 0
            || user.getAge() > 150;
  }

  public boolean isAccountRegular(String account) {
    return !StringUtils.hasText(account) || !accountIdPattern(account);
  }

  public boolean isPasswordRegular(String password) {
    return !StringUtils.hasText(password) || !passwordPattern(password);
  }

  public boolean accountIdPattern(String str) {
    String pattern = "^\\S{3,8}$";
    return str.matches(pattern) && StringUtils.hasText(str);
  }

  public boolean passwordPattern(String str) {
    String pattern = "^(?=.*[!@#$%^&*()-_=+\\\\|{};:'\",<.>/?])(?!.*[\\\\s]).{8,12}$";
    return str.matches(pattern) && StringUtils.hasText(str);
  }
}
