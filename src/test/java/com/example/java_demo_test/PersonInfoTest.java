package com.example.java_demo_test;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.PersonInfoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;


@SpringBootTest(classes = JavaDemoTestApplication.class)
public class PersonInfoTest {


  @Autowired
  private PersonInfoDao personInfoDao;

  @Test
  public void updateAgeByName() {
    personInfoDao.updateAgeByName(20, "小七");

  }

  // 為空不顯示
  @Test
  public void findByNameOrCity1() {
    String name = "";
    String city = "";

    name = !StringUtils.hasText(name) ? null : name;
    city = !StringUtils.hasText(city) ? null : city;
    System.out.println();

    List<PersonInfo> temp = personInfoDao.findByNameOrCity(name, city);
    for (PersonInfo p : temp) {
      System.out.print(p.getName() + " ");
      System.out.print(p.getAge() + " ");
      System.out.print(p.getCity() + " ");
      System.out.println(" ");
    }
  }

  // 為空顯示
  @Test
  public void findByNameOrCity2() {
    String name = "老";
    String city = "";

    boolean hasText = !StringUtils.hasText(name) && !StringUtils.hasText(city);
    name = hasText ? "" : StringUtils.hasText(name) ? name : null;
    city = hasText ? "" : StringUtils.hasText(city) ? city : null;

    List<PersonInfo> temp = personInfoDao.findByNameOrCity(name, city);
    for (PersonInfo p : temp) {
      System.out.print(p.getName() + " ");
      System.out.print(p.getAge() + " ");
      System.out.print(p.getCity() + " ");
      System.out.println(" ");
    }
  }

  // 為空不顯示
  @Test
  public void findByNameOrCityWithRegexp1() {
    String name = "老";
    String city = "台";

    name = !StringUtils.hasText(name) ? null : name;
    city = !StringUtils.hasText(city) ? null : city;

    List<PersonInfo> temp = personInfoDao.findByNameOrCityWithRegexp(name, city);
    for (PersonInfo p : temp) {
      System.out.print(p.getName() + " ");
      System.out.print(p.getAge() + " ");
      System.out.print(p.getCity() + " ");
      System.out.println(" ");
    }
  }

  // 為空都顯示
  @Test
  public void findByNameOrCityWithRegexp2() {
    String name = "老";
    String city = "";

    boolean hasText = !StringUtils.hasText(name) && !StringUtils.hasText(city);
    name = hasText ? "|" : StringUtils.hasText(name) ? name : null;
    city = hasText ? "|" : StringUtils.hasText(city) ? city : null;

    List<PersonInfo> temp = personInfoDao.findByNameOrCityWithRegexp(name, city);
    for (PersonInfo p : temp) {
      System.out.print(p.getName() + " ");
      System.out.print(p.getAge() + " ");
      System.out.print(p.getCity() + " ");
      System.out.println(" ");
    }
  }

  // 為空不顯示
  @Test
  public void findByNameContainingOrCityContaining1() {
    String name = "老";
    String city = "";

    name = !StringUtils.hasText(name) ? "" : name;
    city = !StringUtils.hasText(city) ? "" : city;

    List<PersonInfo> temp = personInfoDao.findByNameContainingOrCityContaining(name, city);
    for (PersonInfo p : temp) {
      System.out.print(p.getName() + " ");
      System.out.print(p.getAge() + " ");
      System.out.print(p.getCity() + " ");
      System.out.println(" ");
    }
  }

  // 為空顯示
  @Test
  public void findByNameContainingOrCityContaining2() {
    String name = "老";
    String city = "";

    boolean hasText = !StringUtils.hasText(name) && !StringUtils.hasText(city);
    name = hasText ? "" : StringUtils.hasText(name) ? name : null;
    city = hasText ? "" : StringUtils.hasText(city) ? city : null;

    List<PersonInfo> temp = personInfoDao.findByNameContainingOrCityContaining(name, city);
    for (PersonInfo p : temp) {
      System.out.print(p.getName() + " ");
      System.out.print(p.getAge() + " ");
      System.out.print(p.getCity() + " ");
      System.out.println(" ");
    }
  }
}

