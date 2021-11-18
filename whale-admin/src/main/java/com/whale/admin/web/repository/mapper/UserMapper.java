package com.whale.admin.web.repository.mapper;

import com.whale.admin.web.repository.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

//    @Select("SELECT * FROM user WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

//    @Insert("INSERT INTO user(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

}