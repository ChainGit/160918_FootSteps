package com.chain.sm.mapper;

import com.chain.sm.entities.User;

import java.util.List;

public interface UserMapper {

    public int add(User user);

    public int update(User user);

    public int deleteById(int id);

    public User getById(int id);

    public List<User> getAll();
}
