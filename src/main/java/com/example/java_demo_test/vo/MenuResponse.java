package com.example.java_demo_test.vo;

import com.example.java_demo_test.entity.Menu;

import java.util.List;
import java.util.Map;

public class MenuResponse {
  private List<Menu> menu;
  private List<Integer> quantityTotalPrice;
  private String response;
  private int sum;
  private Map<String, Integer> orderMap;
  private int totalPrice;
  private List<Integer> quantityTotalPriceList;

  public MenuResponse() {
  }

  public MenuResponse(List<Menu> menu, String response) {
    this.menu = menu;
    this.response = response;
  }

  public MenuResponse(List<Menu> menu, List<Integer> quantityTotalPrice, String response) {
    this.menu = menu;
    this.quantityTotalPrice = quantityTotalPrice;
    this.response = response;
  }

  public MenuResponse(Map<String, Integer> orderMapList, int totalPrice, String response) {
    this.orderMap = orderMap;
    this.totalPrice = totalPrice;
    this.response = response;
  }

  public MenuResponse(List<Menu> menu, List<Integer> quantityTotalPrice, int sum, String response) {
    this.menu = menu;
    this.response = response;
    this.sum = sum;
    this.quantityTotalPrice = quantityTotalPrice;
  }

  public MenuResponse(String response) {
    this.response = response;
  }

  public MenuResponse(List<Menu> menu, List<Integer> quantityTotalPrice, List<Integer> quantityTotalPriceList, int sum, String response) {
    this.menu = menu;
    this.quantityTotalPrice = quantityTotalPrice;
    this.response = response;
    this.sum = sum;
    this.quantityTotalPriceList = quantityTotalPriceList;
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  public List<Integer> getQuantityTotalPrice() {
    return quantityTotalPrice;
  }

  public List<Menu> getMenu() {
    return menu;
  }

  public void setMenu(List<Menu> menu) {
    this.menu = menu;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

}

