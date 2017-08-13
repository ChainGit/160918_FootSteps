package com.tgweb.springmvc.dao;

import com.tgweb.springmvc.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    public Employee getById(int id);

    public List<Employee> getAll();

    public int deleteById(int id);

    public int add(Employee e);

    public int update(Employee e);
}
