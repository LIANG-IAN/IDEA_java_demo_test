package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {


  @Query(value = "SELECT AUTO_INCREMENT " +
          "FROM information_schema.TABLES " +
          "WHERE TABLE_NAME = 'users' " +
          "  AND TABLE_SCHEMA = 'idea_java_demo_test'", nativeQuery = true)
  public int findLastId();

  @Query(value = "SHOW TABLE STATUS LIKE 'users';", nativeQuery = true)
  public String findStatus();


}
