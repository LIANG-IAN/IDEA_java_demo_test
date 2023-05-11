package com.example.java_demo_test;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = JavaDemoTestApplication.class)
class BankTest {
  //@Autowired 使用spring託管的資料
  @Autowired
  private BankDao bankDao;
  @Autowired
  private BankService bankService;

  @Test
  void addBankInfo() {
    Bank bank = new Bank("A01", "A1110", 1000);
    //save 新增資料到bank這張資料庫的表
    bankDao.save(bank);
  }

  @Test
  public void addInfoTest() {
    Bank bank = new Bank("AA999", "AA123456@", 2000);
    bankService.addInfo(bank);
  }

  @Test
  //要來測試 getAmountById()方法有沒有正常運行
  public void getAmountByIdTest() {
    Bank bank = bankService.getAmountById("A01");
    System.out.println(bank.getAmount() + " " + bank.getAccount());
  }

  @Test
  public void depositTest() {
    //先創個新Bank並存入資料庫，方便測試各種方法
    Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000));
    //存款
    Bank newBank = new Bank("AA999", "AA123456@", 3000);
    BankResponse response = bankService.deposit(newBank);
    //從response裡面把Bank屬性的變數拉出來存入resultBank裡面
    Bank resultBank = response.getBank();
    //確認金額準確無誤存進去
    Assert.isTrue(resultBank.getAmount() == oldBank.getAmount() + newBank.getAmount(), "存款失敗");
    Assert.isTrue(response.getMessage().equals("存款成功"), "存款失敗");
    //測完可以刪了
    //bankDao.delete(resBank);
  }

  @Test
  public void withdrawTest() {
    //先創個新Bank並存入資料庫，方便測試各種方法
    Bank oldBank = bankDao.save(new Bank("AA999", "AA123456@", 2000));
    //存款
    Bank newBank = new Bank("AA999", "AA123456@", 2500);
    BankResponse response = bankService.withdraw(newBank);
    //從response裡面把Bank屬性的變數拉出來存入resultBank裡面
    Bank resultBank = response.getBank();
    //確認金額準確無誤存進去
    Assert.isTrue(resultBank.getAmount() == oldBank.getAmount() - newBank.getAmount(), "提款失敗");
    Assert.isTrue(response.getMessage().equals("提款成功"), "提款失敗");
    //測完可以刪了
    //bankDao.delete(resultBank);
  }
}

