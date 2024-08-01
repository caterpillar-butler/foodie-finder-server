package com.butler.server.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.butler.server.model.entities.User;

@Mapper
public interface UserMapper {

  // 유저 생성
  @Insert("INSERT INTO user (name, email) VALUES (#{name}, #{email})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertUser(User user);

  // 유저 조회 (ID로 조회)
  @Select("SELECT * FROM user WHERE id = #{id}")
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "email", column = "email")
  })
  User selectUserById(Long id);

  // 모든 유저 조회
  @Select("SELECT * FROM user")
  @Results({
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "name"),
      @Result(property = "email", column = "email")
  })
  List<User> selectAllUsers();

  // 유저 업데이트
  @Update("UPDATE user SET name = #{name}, email = #{email} WHERE id = #{id}")
  void updateUser(User user);

  // 유저 삭제
  @Delete("DELETE FROM user WHERE id = #{id}")
  void deleteUser(Long id);

  // 이메일 존재 여부 확인
  @Select("SELECT COUNT(1) > 0 FROM user WHERE email = #{email} LIMIT 1")
  boolean isEmailExist(String email);

  // ------ xml ------

  void insertUserXml(User user);

  User selectUserByIdXml(Long id);

  List<User> selectAllUsersXml();

  void updateUserXml(User user);

  void deleteUserXml(Long id);

}