package com.example.java_demo_test.repository;


import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class BaseDao {

  @PersistenceContext
  private EntityManager entityManager;

  @SuppressWarnings({"unchecked", "rawtypes"})
  // Map<String, Object> 就跟 @Query("xxxx") Sting xxx是一樣的意思
  // 第一個 <EntityType> 是泛型，
  protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz) {
    Query query = entityManager.createQuery(sql, clazz);
    // 第一種寫法
    // 檢查是否為空直
    if (!CollectionUtils.isEmpty(params)) {
      for (Map.Entry<String, Object> item : params.entrySet()) {
        query.setParameter(item.getKey(), item.getValue());
      }
      // 第二種寫法
      // 會有黃蚯蚓警告很正常，在最上方加入 @SuppressWarnings，告訴編譯器忽略警示
   /* for (Parameter p : query.getParameters()) {
      query.setParameter(p, params.get(p.getName()));
    }*/
    }

    return query.getResultList();
  }

  @SuppressWarnings({"unchecked"})
  protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz, int limit) {
    Query query = entityManager.createQuery(sql, clazz);
    if (!CollectionUtils.isEmpty(params)) {
      for (Map.Entry<String, Object> item : params.entrySet()) {
        query.setParameter(item.getKey(), item.getValue());
      }
    }
    if (limit > 0) {
      query.setMaxResults(limit);
    }
    return query.getResultList();
  }

  // 每頁的起始位置
  @SuppressWarnings({"unchecked"})
  protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz, int limit, int startPosition) {
    Query query = entityManager.createQuery(sql, clazz);
    if (!CollectionUtils.isEmpty(params)) {
      for (Map.Entry<String, Object> item : params.entrySet()) {
        query.setParameter(item.getKey(), item.getValue());
      }
    }
    if (limit > 0) {
      query.setMaxResults(limit);
    }
    if (startPosition >= 0) {
      query.setFirstResult(startPosition);
    }
    return query.getResultList();
  }

  protected int updateAgeByName(String sql, Map<String, Object> params) {
    Query query = entityManager.createQuery(sql);
    if (!CollectionUtils.isEmpty(params)) {
      for (Map.Entry<String, Object> item : params.entrySet()) {
        query.setParameter(item.getKey(), item.getValue());
      }
    }
    return query.executeUpdate();
  }
}
