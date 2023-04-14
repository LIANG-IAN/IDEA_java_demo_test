package com.example.java_demo_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
  @Id
  @Column(name = "account")
  private String account;
  @Column(name = "pwd")
  private String password;
  @Column(name = "amount")
  private int amount;
  public Bank() {
  }
  public Bank(String account, String password, int amount) {
    this.account = account;
    this.password = password;
    this.amount = amount;
  }
  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }
  public String getAccount() {
    return account;
  }
  public void setAccount(String account) {
    this.account = account;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
