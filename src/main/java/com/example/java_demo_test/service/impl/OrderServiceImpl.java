package com.example.java_demo_test.service.impl;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.repository.MenuDao;
import com.example.java_demo_test.service.ifs.OrderService;
import com.example.java_demo_test.vo.MenuResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
  private final MenuDao menuDao;

  public OrderServiceImpl(MenuDao menuDao) {
    this.menuDao = menuDao;
  }

  @Override
  //如果不用動態變數，而是使用List<Menu>menu
  //防呆必須設計不能是null以及不能是「空集合」
  //Collections.isEmpty(menu) 等於 menu==null&&menu.isEmpty
  public MenuResponse myAddMenu(Menu... menu) {
    if (menu == null) {
      return new MenuResponse(new ArrayList<>(), "您輸入null");
    }
    for (Menu m : menu) {
      if (m.getMeal() == null) {
        return new MenuResponse(new ArrayList<>(), "您輸入null");
      }
      if (m.getMeal().isBlank()) {
        return new MenuResponse(new ArrayList<>(), "您輸入空白值");
      }
      //上面兩個防呆，可以改成一行
      //!StringUtils.hasText(m.getMeal())
      //isBlank只能在java11以上使用
      //StringUtils則透過Spring Framework使用，只要導包任何版本都可使用
      Optional<Menu> optionalMenu = menuDao.findById(m.getMeal());
      if (optionalMenu.isPresent()) {
        return new MenuResponse(new ArrayList<>(), "餐點已存在");
      }
      if (m.getPrice() <= 0) {
        return new MenuResponse(new ArrayList<>(), "餐廳要賺錢的");
      }
      menuDao.save(m);
      //不建議在迴圈中對資料庫存儲，會消耗效能
      //如果今天是List<Menu>menu資料進來，就可以在迴圈外寫menuDao.saveAll(menu)
      //但我是使用動態變數...menu，不適用這方法
    }
    return new MenuResponse(new ArrayList<>(), "儲存成功");
  }

  @Override
  public MenuResponse getAllMenus() {
    List<Menu> allMenu = menuDao.findAll();
    MenuResponse resultMenu = new MenuResponse();
    if (allMenu.isEmpty()) {
      return new MenuResponse(new ArrayList<>(), "目前無菜單");
    }
    resultMenu.setMenu(allMenu);
    resultMenu.setResponse("成功");
    return resultMenu;
  }

  @Override
  public MenuResponse getMenu(String meal) {
    if (!StringUtils.hasText(meal)) {
      return new MenuResponse("輸入錯誤料理名");
    }
    if (!menuDao.existsById(meal)) {
      return new MenuResponse("無此料理");
    }
    List<Menu> menu = new ArrayList<>();
    Optional<Menu> optionalMenu = menuDao.findById(meal);
    if (optionalMenu.isEmpty()) {
      return new MenuResponse("無此料理");
    }
    menu.add(optionalMenu.get());
    return new MenuResponse(menu, "尋找成功");
  }

  @Override
  public MenuResponse myOrderMeal(Menu... order) {
    if (order == null) {
      return new MenuResponse("您輸入null");
    }
    //儲存數量
    List<Integer> Quantity = new ArrayList<>();
    //儲存單項餐點總價
    List<Integer> quantityTotalPriceList = new ArrayList<>();
    //單儲存餐點名
    List<String> mealName = new ArrayList<>();
    int sum = 0;
    for (Menu m : order) {
      if (!StringUtils.hasText(m.getMeal())) {
        return new MenuResponse( "您輸入null");
      }
      //這裡的price是餐點數量
      if (m.getPrice() <= 0) {
        return new MenuResponse("輸入餐點份數有誤");
      }
      //餐點名還沒防呆，但先存入List裡面
      mealName.add(m.getMeal());
    }
    //透過findAllById篩選出正確的料理名稱
    //mealNameFilter這時只存有String
    //儲存正確餐點名及價錢
    List<Menu> mealNameFilter = menuDao.findAllById(mealName);
    if (mealNameFilter.size() == 0) {
      return new MenuResponse("查無料理名");
    }
    for (Menu m1 : mealNameFilter) {
      for (Menu m2 : order) {
        if (m1.getMeal().equals(m2.getMeal())) {
          //m1取得單價再乘上m2取得的餐點數量，得到單項餐點總價
          int quantityPricePrice = m1.getPrice() * m2.getPrice();
          quantityTotalPriceList.add(quantityPricePrice);
          //得到餐點數量
          Quantity.add(m2.getPrice());
          //計算總價
          sum = sum > 500 ? (int) (quantityPricePrice * 0.9) : quantityPricePrice;
        }
      }
    }
    return new MenuResponse(mealNameFilter, quantityTotalPriceList, Quantity, sum, "點餐成功");
  }

  //老師寫法

  @Override
  public MenuResponse teacherAddMenu(List<Menu> menuList) {
    if (CollectionUtils.isEmpty(menuList)) {
      return new MenuResponse("新增餐點錯誤");
    }
    for (Menu m : menuList) {
      if (!StringUtils.hasText(m.getMeal())) {
        return new MenuResponse(new ArrayList<>(), "餐點名稱為空");
      }
      if (m.getPrice() <= 0) {
        return new MenuResponse(new ArrayList<>(), "餐點價格錯誤");
      }
    }
    List<Menu> response = menuDao.saveAll(menuList);
    return new MenuResponse(response, "新增餐點成功");
  }

  @Override
  public MenuResponse 老師orderMeal(Map<String, Integer> orderMap) {
    List<String> itemList = new ArrayList<>();
    int totalPrice = 0;
    Map<String, Integer> finalOrderMap = new HashMap<>();
    for (Map.Entry<String, Integer> item : orderMap.entrySet()) {
      //檢查餐點數量
      if (item.getValue() < 0) {
        return new MenuResponse("餐點數量錯誤");
      }
      //獲得餐點名稱
      itemList.add(item.getKey());
    }
    //利用MySQL特性，輸入的ID如沒有符合就不返回
    //不上面迴圈防呆，而是下面集合起來防呆，可以減少資料庫讀取次數
    List<Menu> result = menuDao.findAllById(itemList);
    for (Menu menu : result) {
      String item = menu.getMeal();
      int price = menu.getPrice();
      for (Map.Entry<String, Integer> map : orderMap.entrySet()) {
        String key = map.getKey();
        int value = map.getValue();
        if ((item.equals(key))) {
          int quantityTotalPrice = value * price;
          finalOrderMap.put(key, value);
          totalPrice += quantityTotalPrice;
        }
      }
    }

    totalPrice = totalPrice > 500 ? (int) (totalPrice * 0.9) : totalPrice;

    return new MenuResponse(finalOrderMap, totalPrice, "點餐成功");
  }

  @Override
  public MenuResponse updateMenuPrice(List<Menu> menuList) {
    MenuResponse menuResponse = new MenuResponse();
    List<String> mealList = new ArrayList<>();
    //CollectionUtils.isEmpty()方法可以篩選掉null以及空的 new ArrayList
    //雖然下方.findAllById()可以攔截到空的List
    //但會多近一次資料庫
    if (CollectionUtils.isEmpty(menuList)) {
      return new MenuResponse("輸入錯誤料理名");
    }
    for (Menu menu : menuList) {
      //這段可省略，下方.findAllById()可以涵蓋到
      //if (!StringUtils.hasText(menu.getMeal())) {
      //  return new MenuResponse("輸入錯誤料理名");
      //}
      //檢查輸入的新價錢
      int price = menu.getPrice();
      if (price < 0) {
        return new MenuResponse("餐廳還要倒貼錢呀!");
      }
      //保留更改價錢的料理名
      String meal = menu.getMeal();
      //還沒防呆料理名，但先存入List裡帶出迴圈
      mealList.add(meal);
    }
    //利用資料庫特性，篩選輸入料理名是資料庫裡有的名稱
    //.findAllById()方法
    //listMenu這時只存有料理名稱，就是一堆String
    List<Menu> listMenu = menuDao.findAllById(mealList);
    if (listMenu.size() == 0) {
      return new MenuResponse("查無料理名");
    }

    List<Menu> newMenuList = new ArrayList<>();
    //使用迴圈來取得listMenu裡面的料理名
    for (Menu menu : listMenu) {
      //使用迴圈來取得menuList裡面的<Menu>們
      for (Menu menu1 : menuList) {
        //比對listMenu裡的名稱與menuList名稱是否相符
        if (menu.getMeal().equals(menu1.getMeal())) {
          //名稱相符及保存menuList裡面的<Menu>
          newMenuList.add(menu1);
        }
      }
    }
    //價錢及料理名都沒問題存入資料庫
    menuDao.saveAll(newMenuList);
    menuResponse.setResponse("更改菜單成功");
    menuResponse.setMenu(newMenuList);
    return menuResponse;
  }


}
