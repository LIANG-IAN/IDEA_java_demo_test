package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Dao 資料處理物件
//一律歸為資料存取庫 @Repository
@Repository
public interface BankDao extends JpaRepository<Bank, String> {

Bank findByAccountAndPassword(String account, String pwd);










}
