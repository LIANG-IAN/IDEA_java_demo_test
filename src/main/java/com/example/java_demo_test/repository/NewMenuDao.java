package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.NewMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewMenuDao extends JpaRepository<NewMenu, Integer> {

}
