package com.example.java_demo_test.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class NewMenu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "saq")
  private int saq;

  @Column(name = "category")
  private String category;

  @Column(name = "item")
  private String item;

  @Column(name = "price")
  private int price;

  @Column(name = "uuid")
  @Type(type = "uuid-char")
  private UUID uuid = UUID.randomUUID();

  public NewMenu() {
  }

  public NewMenu(String category, String item, int price) {
    this.category = category;
    this.item = item;
    this.price = price;
  }

  public NewMenu(String category, String item, int price, UUID uuid) {
    this.category = category;
    this.item = item;
    this.price = price;
    this.uuid = uuid;
  }

  public int getSeq() {
    return saq;
  }

  public void setSeq(int seq) {
    this.saq = seq;
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

  public void setItem(String itme) {
    this.item = itme;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
