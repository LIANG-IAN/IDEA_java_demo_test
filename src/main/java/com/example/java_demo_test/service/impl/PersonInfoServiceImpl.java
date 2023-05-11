package com.example.java_demo_test.service.impl;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.repository.PersonInfoDao;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.PersonInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

  @Autowired
  PersonInfoDao personInfoDao;

  /*@Override
  public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfos) {
    //儲存id
    List<String> idList = new ArrayList<>();
    //檢查personInfos不是null、空集合
    if (CollectionUtils.isEmpty(personInfos)) {
      return new PersonInfoResponse("新增資訊錯誤");
    }
    //檢查List
    //不是空格、空字串、NULL、換行符號
    for (PersonInfo personInfo : personInfos) {
      if (personInfo == null
              ||!StringUtils.hasText(personInfo.getName())
              || !StringUtils.hasText(personInfo.getId())
              || !StringUtils.hasText(personInfo.getCity())) {
        return new PersonInfoResponse("新增資訊錯誤");
      }

      if (personInfo.getAge() < 0) {
        return new PersonInfoResponse("年齡錯誤");
      }
      idList.add(personInfo.getId());
    }
    //檢查id，是否跟資料庫相符
    //比對完後存入filterIdList
    List<PersonInfo> filterIdList = new ArrayList<>();
    filterIdList = personInfoDao.findAllById(idList);
    //檢查id是否已存在
    if (filterIdList.size() > 0) {
      List<String> resIds = new ArrayList<>();
      for (PersonInfo item : filterIdList) {
        resIds.add(item.getId());
      }
      List<PersonInfo> saveList = new ArrayList<>();
      for (PersonInfo item : personInfos) {
        if (!resIds.contains(item.getId())) {
          saveList.add(item);
        }
      }
      personInfoDao.saveAll(saveList);
      return new PersonInfoResponse("新增成功");
    }
    personInfoDao.saveAll(personInfos);
    return new PersonInfoResponse("新增成功");
  }*/

  @Override
  public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfos) {
    //儲存id
    List<String> idList = new ArrayList<>();
    //檢查personInfos不是null、空集合
    if (CollectionUtils.isEmpty(personInfos)) {
      return new PersonInfoResponse("新增資訊錯誤");
    }
    //檢查List
    //不是空格、空字串、NULL、換行符號
    for (PersonInfo personInfo : personInfos) {
      if (personInfo == null
              || !StringUtils.hasText(personInfo.getName())
              || !StringUtils.hasText(personInfo.getId())
              || !StringUtils.hasText(personInfo.getCity())) {
        return new PersonInfoResponse("新增資訊錯誤");
      }

      if (personInfo.getAge() < 0) {
        return new PersonInfoResponse("年齡錯誤");
      }
      idList.add(personInfo.getId());
    }
    //檢查id，是否跟資料庫相符
    //比對完後存入filterIdList
    List<PersonInfo> filterIdList = personInfoDao.findAllById(idList);
    //確認比對完存入的不是空集合
    if (!filterIdList.isEmpty()) {
      //.removeAll() 剔除已存在資料庫的資料
      //需要重寫equals跟hashCode方法
      personInfos.removeAll(filterIdList);
    }
    String message = "";
    int count = filterIdList.size();
    if (count > 0) {
      message = "，重複" + count + "筆資料，已剔除重複資料";
    }
    //用lambda實現上面
    //filterIdList.stream().filter(item->idList.contains(item.getId())).collect(Collectors.toList());

    personInfoDao.saveAll(personInfos);
    return new PersonInfoResponse(personInfos, "新增資訊成功" + message);
  }

  @Override
  public PersonInfoResponse getPersonInfo() {
    List<PersonInfo> allPersonInfo = personInfoDao.findAll();
    PersonInfoResponse resultPersonInfo = new PersonInfoResponse();
    if (allPersonInfo.isEmpty()) {
      return new PersonInfoResponse("目前無資料");
    }
    resultPersonInfo.setPersonInfoResponseList(allPersonInfo);
    resultPersonInfo.setMessage("成功");
    return resultPersonInfo;
  }

  @Override
  public PersonInfoResponse getPersonInfoById(String personInfoId) {
    if (!StringUtils.hasText(personInfoId)) {
      return new PersonInfoResponse("ID錯誤格式");
    }
    Optional<PersonInfo> opPersonInfo = personInfoDao.findById(personInfoId);
    if (!opPersonInfo.isPresent()) {
      return new PersonInfoResponse("查無此人");
    }
    return new PersonInfoResponse(opPersonInfo.get(), "取得成功");
  }

  /*  @Override
    public PersonInfoResponse findPersonInfoByAge(int age) {
      List<PersonInfo> allPersonInfoList = personInfoDao.findAll();
      List<PersonInfo> personInfoListByAge = new ArrayList<>();

      for (PersonInfo personInfo : allPersonInfoList) {
        if (personInfo.getAge() > age) {
          personInfoListByAge.add(personInfo);
        }
      }
      if (personInfoListByAge.size() == 0) {
        return new PersonInfoResponse("無相符資料");
      }
      return new PersonInfoResponse(personInfoListByAge, "尋找成功");
    }*/

  public  PersonInfoResponse findByAgeLessThanEqualOrderByAge(int age){
    return new PersonInfoResponse(personInfoDao.findByAgeLessThanEqualOrderByAge(age),"取得成功");
  }

  public PersonInfoResponse findTop3ByAgeBetweenOrderByAgeDesc(int num1,int num2){
    return new PersonInfoResponse(personInfoDao.findTop3ByAgeBetweenOrderByAgeDesc(num1,num2),"取得成功");
  }

 public PersonInfoResponse findByCityContaining (String keyWorld){
   return new PersonInfoResponse(personInfoDao.findByCityContaining(keyWorld),"取得成功");
 }

 public PersonInfoResponse findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int num1,String keyWorld){return new PersonInfoResponse(personInfoDao.findByAgeGreaterThanAndCityContainingOrderByAgeDesc(num1,keyWorld),"取得成功");

 }

}

