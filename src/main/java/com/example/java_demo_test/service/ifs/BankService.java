package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.vo.BankResponse;

public interface BankService {
   void addInfo(Bank bank);
   Bank getAmountById(String id);
   BankResponse deposit(Bank bank);

   BankResponse withdraw(Bank bank);
}

