package com.example.java_demo_test.service.ifs;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.vo.MenuResponse;

import java.util.List;
import java.util.Map;


public interface OrderService {
  MenuResponse myAddMenu(Menu... menu);

  MenuResponse getAllMenus();

  MenuResponse getMenu(String meal);

  MenuResponse myOrderMeal(Menu... order);

  //4.18作業
  MenuResponse updateMenuPrice(List<Menu> menuList);

  //老師寫法
  MenuResponse teacherAddMenu(List<Menu> menuList);

  MenuResponse 老師orderMeal(Map<String, Integer> orderMap);

}
