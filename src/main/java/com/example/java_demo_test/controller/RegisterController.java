package com.example.java_demo_test.controller;

import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.ActiveRequest;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RegisterController {

  @Autowired
  private RegisterService registerService;

  @PostMapping(value = "register")
  public RegisterResponse register(@RequestBody RegisterRequest request) {
    return registerService.register(request);
  }

  @PostMapping(value = "active")
  public RegisterResponse active(@RequestBody ActiveRequest activeRequest) {
    return registerService.active(activeRequest);
  }

  @PostMapping(value = "login")
  public RegisterResponse login(@RequestBody RegisterRequest request, HttpSession session) {
    RegisterResponse res = registerService.login(request);
    if (res.getMessage().equals("成功!")){
      int verifyCode = (int)Math.round(Math.random()*10000);
      session.setAttribute("verifyCode",verifyCode);
      session.setAttribute("account",request.getAccount());
      session.setAttribute("pwd",request.getPwd());
      session.setMaxInactiveInterval(300); // 單位秒
      res.setSessionId(session.getId());
      res.setVerifyCode(verifyCode);
    }
    return res;
  }

  @PostMapping(value = "get_reg_time")
  public RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
    return registerService.getRegTime(request);
  }

  @PostMapping(value = "get_reg_time_by_session")
  public RegisterResponse getRegTimeBySession(@RequestBody RegisterRequest request,HttpSession session) {
    String account = (String) session.getAttribute("account");
    String pwd = (String) session.getAttribute("pwd");
    if (!StringUtils.hasText(account)||!StringUtils.hasText(pwd)){
      return new RegisterResponse("請先登錄");
    }
    // session取出的值是Object，所以session的預設值是null
    // 無登錄或驗證碼時效過期兩種情況，.getAttribute()取出將會是null
    // 所以不能用基本資料型態int去接，同時需要做null的防呆
    Integer verifyCode = (Integer) session.getAttribute("verifyCode");
    if (verifyCode == null ||verifyCode != request.getVerifyCode()){
      return new RegisterResponse("驗證碼不正確");
    }
    return registerService.getRegTimeBySession(account,pwd);
  }

  // 邏輯判斷寫在Impl層
  @PostMapping(value = "get_reg_time_by_session2")
  public RegisterResponse getRegTimeBySession2(@RequestBody RegisterRequest request,HttpSession session) {
    String account = (String) session.getAttribute("account");
    String pwd = (String) session.getAttribute("pwd");
    Integer verifyCode = (Integer) session.getAttribute("verifyCode");

    return   registerService.getRegTimeBySession2(request,account,pwd,verifyCode);
  }


}
