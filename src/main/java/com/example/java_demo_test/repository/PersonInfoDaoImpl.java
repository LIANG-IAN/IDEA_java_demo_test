package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.BaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonInfoDaoImpl extends BaseDao {

  public List<PersonInfo> doQueryByAge(int age) {
    StringBuffer sb = new StringBuffer();
    sb.append("select p from PersonInfo p where p.age >= :inputAge");
    Map<String, Object> params = new HashMap<>();

    // key值位置放上參數
    params.put("inputAge", age);

    // 記住最後面是放自訂類，但要加上.class
    return doQuery(sb.toString(), params, PersonInfo.class);

  }

  public List<PersonInfo> doQueryByAge(int age, int limit) {
    StringBuffer sb = new StringBuffer();
    sb.append("select p from PersonInfo p where p.age >= :inputAge");
    Map<String, Object> params = new HashMap<>();
    // key值位置放上參數
    params.put("inputAge", age);
    // 記住最後面是放自訂類，但要加上.class
    return doQuery(sb.toString(), params, PersonInfo.class, limit);
  }

  public List<PersonInfo> doQueryByAge(int age, int limit, int startPosition) {
    StringBuffer sb = new StringBuffer();
    sb.append("select p from PersonInfo p where p.age >= :inputAge");
    Map<String, Object> params = new HashMap<>();
    // key值位置放上參數
    params.put("inputAge", age);
    // 記住最後面是放自訂類，但要加上.class
    return doQuery(sb.toString(), params, PersonInfo.class, limit, startPosition);
  }

  public int updateAgeByName(int age, String name) {
    StringBuffer sb = new StringBuffer();
    sb.append("update PersonInfo set age = :age where name = :name");
    Map<String, Object> params = new HashMap<>();
    params.put("age", age);
    params.put("name", name);
    return updateAgeByName(sb.toString(),params);

  }

}
