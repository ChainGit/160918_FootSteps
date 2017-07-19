package com.tgweb.ssh.dao;

import com.tgweb.ssh.entities.Employee;

import java.util.List;

/**
 * Created by Chain on 2017/7/17.
 */
public interface EmployeeDao extends BaseDao {

    public List<Employee> getAllEmployees();

    public boolean deleteEmployeeById(Long id);

    public boolean saveOrUpdate(Employee employee);

    public Employee getEmployeeById(Long id);

    public Employee getEmployeeByName(String name);

    public Employee getEmployeeByEId(String eId);
}
