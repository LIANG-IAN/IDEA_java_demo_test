package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterDao extends JpaRepository<Register,String> {

public Register findByAccountAndPwd(String account,String pwd);

public Register findByAccountAndPwdAndActive(String account,String pwd,boolean active);



}
