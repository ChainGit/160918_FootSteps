package com.tgweb.ssh.service.impl;

import com.tgweb.ssh.dao.DepartmentDao;
import com.tgweb.ssh.dao.EmployeeDao;
import com.tgweb.ssh.entities.Department;
import com.tgweb.ssh.entities.Employee;
import com.tgweb.ssh.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chain on 2017/7/17.
 */
@Service("companyInfoService")
public class CompanyInfoServiceImpl implements CompanyInfoService {


    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        return employeeDao.deleteEmployeeById(id);
    }

    @Override
    public boolean saveOrUpdateEmployee(Employee employee) {
        return employeeDao.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return employeeDao.getEmployeeByName(name);
    }

    @Override
    public Employee getEmployeeByEId(String eId) {
        return employeeDao.getEmployeeByEId(eId);
    }
}
