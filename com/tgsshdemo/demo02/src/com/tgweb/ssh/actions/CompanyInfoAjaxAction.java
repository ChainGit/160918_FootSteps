package com.tgweb.ssh.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tgweb.ssh.entities.Department;
import com.tgweb.ssh.entities.Employee;
import com.tgweb.ssh.service.CompanyInfoService;
import com.tgweb.ssh.utils.DateUtils;
import org.apache.struts2.interceptor.RequestAware;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chain on 2017/7/18.
 */
public class CompanyInfoAjaxAction extends ActionSupport implements RequestAware, ModelDriven<Employee>, Preparable {

    private CompanyInfoService companyInfoService;

    private Map<String, Object> requestMap;

    private InputStream inputStream;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Employee employee;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setCompanyInfoService(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    public CompanyInfoService getCompanyInfoService() {
        return companyInfoService;
    }

    public String listAll() {
        List<Employee> emps0 = getCompanyInfoService().getAllEmployees();
        List<Employee> emps = new ArrayList<>();
        for (Employee e : emps0) {
            Department de = e.getDepartment();
            Department dp = null;
            if (de != null)
                dp = new Department(de.getId(), de.getDepartmentId(), de.getDepartmentName(), null);
            Employee ep = new Employee(e.getId(), e.getEmployeeId(), e.getEmployeeName(), e.getEmployeeEmail(), e.getEmployeeAge(), e.getEmployeeBirth(), e.getEmployeeCreateTime(), dp);
            emps.add(ep);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("emps", emps);
        try {
            inputStream = new ByteArrayInputStream(objectMapper.writeValueAsBytes(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("employeeSet size: " + emps.size());
        return "listAll";
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    private String eId;

    public String delete() {
        boolean status = companyInfoService.deleteEmployeeById(id);
        System.out.println("delete status: " + status);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        try {
            inputStream = new ByteArrayInputStream(objectMapper.writeValueAsBytes(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "delete";
    }

    public String depts() {
        List<Department> depts = companyInfoService.getAllDepartments();
        System.out.println("depts size: " + depts.size());
        Map<String, Object> map = new HashMap<>();
        map.put("depts", depts);
        try {
            inputStream = new ByteArrayInputStream(objectMapper.writeValueAsBytes(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "depts";
    }

    public String emp() {
        Employee e = companyInfoService.getEmployeeById(id);
        Department de = e.getDepartment();
        Department dp = null;
        if (de != null)
            dp = new Department(de.getId(), de.getDepartmentId(), de.getDepartmentName(), null);
        Employee ep = new Employee(e.getId(), e.getEmployeeId(), e.getEmployeeName(), e.getEmployeeEmail(), e.getEmployeeAge(), e.getEmployeeBirth(), e.getEmployeeCreateTime(), dp);
        Map<String, Object> map = new HashMap<>();
        map.put("emp", ep);
        try {
            inputStream = new ByteArrayInputStream(objectMapper.writeValueAsBytes(map));
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return "emp";
    }

    public String check() {
        boolean status = false;
        if (employee.getId() == 0) {
            Employee e = companyInfoService.getEmployeeByEId(eId);
            status = e == null ? true : false;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        try {
            inputStream = new ByteArrayInputStream(objectMapper.writeValueAsBytes(map));
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return "check";
    }

    public String input() {
        if (employee.getId() == 0) {
            java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
            employee.setEmployeeCreateTime(date);
        }
        java.sql.Date date1 = employee.getEmployeeBirth();
        System.out.println(date1);
        byte age = (byte) DateUtils.getAgeByBirthday(date1);
        employee.setEmployeeAge(age);
        System.out.println(employee);
        companyInfoService.saveOrUpdateEmployee(employee);
        return "input";
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.requestMap = map;
    }


    @Override
    public Employee getModel() {
        if (employee == null)
            employee = new Employee();
        return employee;
    }

    @Override
    public void prepare() throws Exception {
        System.out.printf("prepare");
    }
}
