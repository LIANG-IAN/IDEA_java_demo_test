package com.example.java_demo_test.controller;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.MenuResponse;
import com.example.java_demo_test.vo.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping(value = "my_add_menus")
  MenuResponse myAddMenu(@RequestBody OrderRequest orderRequest) {
    List<Menu> menuList = orderRequest.getMenuList();
    MenuResponse rp = orderService.myAddMenu(menuList.toArray(new Menu[0]));
    return rp;
  }
  @PostMapping(value = "my_order_meal")
  MenuResponse myOrderMeal(@RequestBody OrderRequest orderRequest) {
    List<Menu> menuList = orderRequest.getMenuList();
    MenuResponse response = orderService.myOrderMeal(menuList.toArray(new Menu[0]));
    return response;
  }

  //老師寫法
  @PostMapping(value = "add_menus")
  MenuResponse 老師AddMenu(@RequestBody OrderRequest orderRequest) {
    return orderService.teacherAddMenu(orderRequest.getMenuList());
  }

  @GetMapping(value = "get_all_menus")
  MenuResponse getAllMenus() {
    return orderService.getAllMenus();
  }

  @PostMapping(value = "get_menu")
  MenuResponse getMenu(@RequestBody OrderRequest orderRequest) {
    String meal = orderRequest.getMeal();
    return orderService.getMenu(meal);
  }

  @PostMapping(value = "update_menu_price")
  MenuResponse updateMenuPrice(@RequestBody OrderRequest orderRequest) {
   return orderService.updateMenuPrice(orderRequest.getMenuList());
  }
}



