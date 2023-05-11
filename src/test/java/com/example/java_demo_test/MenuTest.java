package com.example.java_demo_test;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.MenuDao;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.MenuResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = JavaDemoTestApplication.class)
class MenuTest {
  //@Autowired 使用spring託管的資料
  @Autowired
  private OrderService orderService;
  @Autowired
  private MenuDao menuDao;

  @Test
  public void myAddMenu() {
    Menu m1 = new Menu("沙拉", 10);
    Menu m2 = new Menu("water", 0);
    MenuResponse resultMenu = orderService.myAddMenu(m1, m2);
    System.out.println("========================================");
    System.out.println("Response: " + resultMenu.getResponse());
    System.out.println("========================================");
    Assert.isTrue(resultMenu.getResponse() != null, "新增點錯誤");
  }

  @Test
  public void getAllMenus() {
    System.out.println("========================================");
    MenuResponse resultMenus = orderService.getAllMenus();
    List<Menu> menus = resultMenus.getMenu();
    for (Menu m : menus) {
      System.out.println("Meal: " + m.getMeal() + ", Price: " + m.getPrice());
    }
    System.out.println("Response: " + resultMenus.getResponse());
    System.out.println("========================================");
  }

  @Test
  public void getMenu() {
    MenuResponse resultMenu = orderService.getMenu("hot dog");
    List<Menu> menuList = resultMenu.getMenu();
    System.out.println("========================================");
    for (Menu menu : menuList) {
      System.out.println("料理名：" + menu.getMeal());
      System.out.println("價格：" + menu.getPrice());
    }
    System.out.println("Response: " + resultMenu.getResponse());
    System.out.println("========================================");
  }

  @Test
  public void order() {
    Menu m1 = new Menu("拿坡里披薩", 5);
    Menu m2 = new Menu("hot dog", 3);
    Menu m3 = new Menu("♨たまご", 10);

    MenuResponse menuResponse = orderService.myOrderMeal(m1, m2, m3);
    int index = 0;
    System.out.println("餐點名稱列表：");
    for (Menu menu : menuResponse.getMenu()) {
      System.out.println(menu.getMeal() + " x " + menu.getPrice() + "，總價格：" + menuResponse.getQuantityTotalPrice().get(index));
      index++;
    }
    System.out.println("全部餐點總金額：" + menuResponse.getSum());
    System.out.println("回應訊息：" + menuResponse.getResponse());
  }

  @Test
  public void myOrder() {
    Menu m1 = new Menu("沙拉", 5);
    Menu m2 = new Menu("豚骨拉麵", 5);
    MenuResponse menuResponse = orderService.myOrderMeal(m1, m2);
  }
  @Test
  public void test(){
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    Collections.addAll(list1,"A","B","C");
    Collections.addAll(list2,"A","B");
    for (int i = 0; i < list1.size(); i++) {
      for (int i1 = 0; i1 < list2.size(); i1++) {
        if (list1.get(i).equals(list2.get(i1))) {
          list1.remove(i);
          list2.remove(i1);
        }
      }
    }
    System.out.println(list1);
  }

  @Test
  public void deleteTest(){
    menuDao.deleteByPriceEquals(1);
  }

}


