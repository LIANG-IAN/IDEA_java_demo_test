package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Menu;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderRequest {


  //老師寫法
  @JsonProperty("menu_list")
  List<Menu> menuList;

  //我的寫法
  @JsonProperty("meal")
  String meal;

  public String getMeal() {
    return meal;
  }

  public List<Menu> getMenuList() {
    return menuList;
  }

}
