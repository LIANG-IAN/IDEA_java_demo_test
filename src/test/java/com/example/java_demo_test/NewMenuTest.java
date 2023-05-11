package com.example.java_demo_test;

import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.repository.NewMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class NewMenuTest {

  @Autowired
  NewMenuDao newMenuDao;

  public void addDataTest(){
    NewMenu menu1 = new NewMenu("fish","紅燒",120);
    newMenuDao.save(menu1);
  }


}
