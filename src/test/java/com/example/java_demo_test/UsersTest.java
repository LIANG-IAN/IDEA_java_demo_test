package com.example.java_demo_test;

import com.example.java_demo_test.entity.Users;
import com.example.java_demo_test.repository.UsersDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

@SpringBootTest(classes = JavaDemoTestApplication.class)
public class UsersTest {

  @Autowired
  UsersDao usersDao;

  @Test
  public void findLastId() {

    System.out.println(usersDao.findLastId());

    for (int i = 0; i <5; i++) {


      Users newUser = new Users();
      if (usersDao.findAll() == null) {
        newUser.setName("name_01");
      }
      int lastId = usersDao.findLastId() + 1;
      if (lastId < 10) {
        newUser.setName("name_0" + lastId);
      }
      else {
        newUser.setName("name_" + lastId);
      }

      usersDao.save(newUser);
    }
  }

  @Test
  public void findStatus(){
    System.out.println(usersDao.findStatus());
  }

  @Test
  public void test(){
    System.out.println(usersDao.findLastId());
  }
}
