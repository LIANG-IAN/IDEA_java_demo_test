package com.example.java_demo_test.entity;

import java.io.Serializable;

public class NewMenu2Id implements Serializable {

  private static final long serialLVVersionUID = 1L;
  private String category;
  private String item;

  public NewMenu2Id() {
  }

  public NewMenu2Id(String category, String item) {
    this.category = category;
    this.item = item;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }
}
