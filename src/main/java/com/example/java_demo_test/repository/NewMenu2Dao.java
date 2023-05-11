package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.entity.NewMenu2Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewMenu2Dao extends JpaRepository<NewMenu2, NewMenu2Id> {

}
