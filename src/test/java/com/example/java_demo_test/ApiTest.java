package com.example.java_demo_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiTest {

  // GET的方法
  @Test
  @SuppressWarnings("unchecked")
  public void getApiTest() throws JsonProcessingException {
    String targetUrl = "https://opendata.hccg.gov.tw/API/v3/Rest/OpenData/704FC0B2EE7500E4?take={item}&skip={page}}";
    // 對外方送Http請求
    RestTemplate restTemplate = new RestTemplate();


    // 可以用佔位符設定網址中的值
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("item", "50");
    uriVariables.put("page", "0");

    // 接回來資料轉成字串
    // 另一種寫法 String resStr = restTemplate.getForObject(targetUrl,String.class);
    ResponseEntity<String> resString = restTemplate.getForEntity(targetUrl, String.class, uriVariables);
    System.out.println(resString.getStatusCode());
    System.out.println(resString.getStatusCodeValue());
    String responseString = resString.getBody();
    System.out.println(responseString);

    // 透過Map抓取字串內的資料
    ObjectMapper mapper = new ObjectMapper();
    // 紅蚯蚓處生成 throws JsonProcessingException
    List<Map<String, Object>> reList = mapper.readValue(responseString, List.class);
    for (Map<String, Object> map : reList) {
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        System.out.println("key: " + entry.getKey());
        System.out.println("value: " + entry.getValue());
      }
    }
  }

  // POST方法
  @Test
  public void postApiTest() {
    String targetUrl = "http://172.20.10.8:8080/api/register";
    // 對外方送Http請求
    Map<String, String> bodyMap = new HashMap<>();
    bodyMap.put("account", "foodPanda");
    bodyMap.put("pwd", "炭志郎");

    ObjectMapper mapper = new ObjectMapper();
    // 這裡也可以使用上面 @SuppressWarnings("unchecked")
    try {
      String reqBodyStr = mapper.writeValueAsString(bodyMap);
      RestTemplate restTemplate = new RestTemplate();

      // HttpHeaders類型去接收header(表頭)資料
      // header管理整份資料的傳送型態(ContentType)，這裡選擇json格式
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      // 將body的內容與header黏在一起
      HttpEntity<String> requestBody = new HttpEntity<>(reqBodyStr,headers);
      ResponseEntity<String> response = restTemplate.postForEntity(targetUrl,requestBody,String.class);

      System.out.println(response.getStatusCodeValue());
      System.out.println(response.getBody());
    }
    catch (Exception e){

    }
  }

}
