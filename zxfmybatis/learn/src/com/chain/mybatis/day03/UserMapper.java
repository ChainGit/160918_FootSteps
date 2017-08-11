package com.chain.mybatis.day03;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getById(int id);

    @Select("SELECT * FROM user")
    public List<User> getAll();

    @Insert("INSERT INTO user (name,age) VALUES (#{name},#{age})")
    public int add(User user);

    @Insert("DELETE FROM user WHERE id = #{id}")
    public int delete(User user);

    @Insert("DELETE FROM user WHERE id = #{id}")
    public int deleteById(int id);

    @Update("UPDATE user SET name=#{name},age=#{age} WHERE id = #{id}")
    public int update(User user);

}
