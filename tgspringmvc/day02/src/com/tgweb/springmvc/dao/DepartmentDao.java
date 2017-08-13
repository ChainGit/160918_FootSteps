package com.tgweb.springmvc.dao;

import com.tgweb.springmvc.entities.Department;

import java.util.List;

public interface DepartmentDao {

    public Department getById(int id);

    public List<Department> getAll();
}
