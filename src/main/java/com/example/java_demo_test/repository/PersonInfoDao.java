package com.example.java_demo_test.repository;

import com.example.java_demo_test.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, String> {

  public List<PersonInfo> findByAgeGreaterThan(int age);

  public List<PersonInfo> findByAgeLessThanEqualOrderByAge(int age);

  public List<PersonInfo> findTop3ByAgeBetweenOrderByAgeDesc(int num1, int num2);

  public List<PersonInfo> findByCityContaining(String keyWorld);

  public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int num1, String keyWorld);

  public List<PersonInfo> findByAgeLessThanOrAgeGreaterThanOrderByAgeDesc(int num1, int num2);

  public List<PersonInfo> doQueryByAge(int age);

  public List<PersonInfo> doQueryByAge(int age, int limit);

  public List<PersonInfo> doQueryByAge(int age, int limit, int startPosition);
  @Transactional
  public int updateAgeByName(int age, String name);

  @Transactional
  @Modifying
  @Query("update PersonInfo  p set p.id = :newId, p.name = :newName, " +
          "p.age = :newAge, p.city = :newCity where p.id = :newId")
  public int updateNameById(
          @Param("newId") String inputString,
          @Param("newName") String newName,
          @Param("newAge") int newAge,
          @Param("newCity") String newCity
  );

  @Transactional
  @Modifying
  @Query("update PersonInfo  p set p.id = :newId, p.name = :newName, " +
          "p.age = :newAge where p.id = :newId")
  public int updateNameById(
          @Param("newId") String inputString,
          @Param("newName") String newName,
          @Param("newAge") int newAge
  );

  @Query(value = "select * from person_info p where p.name like %:name% or p.city like %:city%",nativeQuery = true)
  public List<PersonInfo> findByNameOrCity(
          @Param("name") String name,
          @Param("city") String city
  );

  @Query(value = "select * from person_info p where p.name regexp :r1 or p.city regexp :r2",nativeQuery = true)
  public List<PersonInfo> findByNameOrCityWithRegexp(
          @Param("r1") String r1,
          @Param("r2") String r2
  );

  public List<PersonInfo> findByNameContainingOrCityContaining(String name,String city);

}
