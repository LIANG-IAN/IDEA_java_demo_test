package com.example.java_demo_test;

import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.repository.NewMenu2Dao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JavaDemoTestApplication.class)
class NewMenu2Test {

  @Autowired
  NewMenu2Dao newMenu2Dao;
@Test
  public void addDataTest() {
    NewMenu2 menu1 = new NewMenu2("fish", "紅燒", 120);
    newMenu2Dao.save(menu1);

  }

}
