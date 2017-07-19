package com.tgweb.ssh.service;

import com.tgweb.ssh.entities.Department;
import com.tgweb.ssh.entities.Employee;

import java.util.List;

/**
 * Created by Chain on 2017/7/17.
 */
public interface CompanyInfoService {

    public List<Employee> getAllEmployees();

    public List<Department> getAllDepartments();

    public boolean deleteEmployeeById(Long id);

    public boolean saveOrUpdateEmployee(Employee employee);

    public Employee getEmployeeById(Long id);

    public Employee getEmployeeByName(String name);

    public Employee getEmployeeByEId(String eId);
}
