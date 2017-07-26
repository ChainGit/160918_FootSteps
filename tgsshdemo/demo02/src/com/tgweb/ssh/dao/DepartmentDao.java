package com.tgweb.ssh.dao;

import com.tgweb.ssh.entities.Department;

import java.util.List;

/**
 * Created by Chain on 2017/7/17.
 */
public interface DepartmentDao extends BaseDao {

    public List<Department> getAllDepartments();

}
