package com.example.java_demo_test.service.impl;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.repository.BankDao;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
  String patternAccount = "(\\w|[^`!#$%^&*]){3,8}";
  String patternPassword = "(\\S){3,8}";
  @Autowired
  private BankDao bankDao;

  @Override
  public void addInfo(Bank bank) {
    checkBankAndAccount(bank);
    checkPassword(bank);
    checkAmount(bank);
    bankDao.save(bank);
  }

  private void checkBankAndAccount(Bank bank) {
    if (bank == null) {
      System.out.println("Bank is null!!");
      return;
    }
    String account = bank.getAccount();
    if (account == null) {
      System.out.println("Account is null!!");
      return;
    }
    if (!account.matches(patternAccount)) {
      System.out.println("Account is error!!");
    }
  }

  private void checkPassword(Bank bank) {
    String password = bank.getPassword();
    if (password == null) {
      System.out.println("Password is null");
      return;
    }
    if (password.matches(patternPassword)) {
      System.out.println("Password is error!!");
    }
  }

  private void checkAmount(Bank bank) {
    if (bank.getAmount() <= 0) {
      System.out.println("Amount is negative!!");
    }
  }

  @Override
  public Bank getAmountById(String id) {
    //這裡id是指Bank.java裡面 @ID 的那個變數
    //也就是 private String account

    //第一層防呆，確保使用者有輸入id沒留白
    //1.一種寫法
    if (!StringUtils.hasText(id)) {
      return new Bank();
    }
    //不是Bank op = bankDao.findById(id);
    //而是Optional<Bank>，因為.findById()只能Optional去接

    Optional<Bank> op = bankDao.findById(id);
    //第二層防呆，資料庫裡抓出來的資料不是null
    if (op.isEmpty()) {
      return new Bank();
    }
    return op.get();

    //2.另一種寫法
    //if (id == null || id.isBlank()){
    //  return new Bank();
    //  }
  }

  @Override
  //存錢
  public BankResponse deposit(Bank bank) {
    //各種防呆
    if (bank == null
            || !StringUtils.hasText(bank.getAccount())
            || !StringUtils.hasText(bank.getPassword())
            || bank.getAmount() <= 0) {
      return new BankResponse(new Bank(), "就不訴你帳號還密碼錯誤");
    }
    //=====================第一種寫法=======================
    //叫出資料庫裡的ID，也就是account
    //Optional op = bankDao.findById(bank.getAccount());
    //又是防呆。使用Optional就是要isPresent防呆，固定的
    //if (!op.isPresent()) {
    //  return new Bank();
    //}
    //Bank result = (Bank) op.get();
    //叫出資料庫裡的password
    //if (!result.getPassword().equals(bank.getPassword())) {
    //  return new Bank();
    //}
    //=====================第二種寫法=======================
    Bank result = bankDao.findByAccountAndPassword(bank.getAccount(), bank.getPassword());
    if (result == null) {
      return new BankResponse(new Bank(), "帳號密碼不存在");
    }
    //====================================================
    //調出資料庫裡的amount加上要存入的amount
    int newAmount = result.getAmount() + bank.getAmount();
    result.setAmount(newAmount);
    //別忘記存回資料庫
    //.save預設會回傳Bank屬性的資料，所以可以直接return出去
    return new BankResponse(bankDao.save(result), "存款成功，這可是保命錢");
  }

  @Override
  public BankResponse withdraw(Bank bank) {
    if (bank == null
            || !StringUtils.hasText(bank.getAccount())
            || !StringUtils.hasText(bank.getPassword())
            || bank.getAmount() <= 0) {
      return new BankResponse(new Bank(), "就不訴你帳號還密碼錯誤");
    }
    Bank result = bankDao.findByAccountAndPassword(bank.getAccount(), bank.getPassword());
    if (result == null) {
      return new BankResponse(new Bank(), "帳號密碼不存在");
    }
    if (bank.getAmount() > result.getAmount()) {
      return new BankResponse(new Bank(), "可憐呀，餘額不足");
    }
    int newAmount = result.getAmount() - bank.getAmount();
    result.setAmount(newAmount);
    return new BankResponse(bankDao.save(result), "提款成功，這個月不用吃土");
  }

}
