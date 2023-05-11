package com.example.java_demo_test.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
  @Id
  @Column(name = "meal")
  private String meal;
  @Column(name = "price")
  private int price;


  public Menu() {
  }

  public Menu(String meal, int price) {
    this.meal = meal;
    this.price = price;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

}
