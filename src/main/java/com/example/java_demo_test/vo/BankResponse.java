package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Bank;

public class BankResponse {
  private Bank bank;
  private String message;

  public BankResponse(Bank bank, String message) {
    this.bank = bank;
    this.message = message;
  }

  public BankResponse() {
  }

  public void setBank(Bank bank){
    this.bank = bank;
  }
  public Bank getBank(){
    return this.bank;
  }

  public String getMessage(){
    return this.message;
  }

  public void setMessage(String message){
    this.message=message;
  }
}
