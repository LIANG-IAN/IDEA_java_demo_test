package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.LoginResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao extends JpaRepository<Login, String> {
  public List<Login> findByCityLikeOrderByAge(String city);
}
