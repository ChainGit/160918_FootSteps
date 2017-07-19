package com.tgweb.ssh.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tgweb.ssh.entities.Department;
import com.tgweb.ssh.entities.Employee;
import com.tgweb.ssh.service.CompanyInfoService;
import com.tgweb.ssh.utils.DateUtils;
import net.sf.ehcache.hibernate.management.api.EhcacheHibernateMBean;
import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

/**
 * Created by Chain on 2017/7/17.
 */
public class CompanyInfoNormalAction extends ActionSupport implements RequestAware, ModelDriven<Employee>, Preparable {

    private CompanyInfoService companyInfoService;

    private Map<String, Object> requestMap;

    private Employee employee;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyInfoService(CompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    public CompanyInfoService getCompanyInfoService() {
        return companyInfoService;
    }

    public String homeUi() {
        List<Employee> emps = getCompanyInfoService().getAllEmployees();
        requestMap.put("emps", emps);
        System.out.println("employeeSet size: " + emps.size());
        return "homeUi-success";
    }

    public String delete() {
        boolean status = companyInfoService.deleteEmployeeById(id);
        System.out.println("delete status: " + status);
        return status ? "delete-success" : "delete-error";
    }

    public String editUi() {
        List<Department> depts = companyInfoService.getAllDepartments();
        requestMap.put("depts", depts);
        System.out.println("depts size: " + depts.size());

        if (id != null) {
            Employee e = companyInfoService.getEmployeeById(id);
            employee.setId(e.getId());
            employee.setEmployeeId(e.getEmployeeId());
            employee.setEmployeeName(e.getEmployeeName());
            employee.setEmployeeEmail(e.getEmployeeEmail());
            employee.setEmployeeAge(e.getEmployeeAge());
            employee.setEmployeeBirth(e.getEmployeeBirth());
            employee.setEmployeeCreateTime(e.getEmployeeCreateTime());
            Department d = null;
            if (e.getDepartment() != null) {
                d = new Department();
                d.setId(e.getDepartment().getId());
            }
            employee.setDepartment(d);
        }

        return "editUi";
    }

    public void prepareEditUi() {
        if (employee == null)
            employee = new Employee();
    }

    public String input() {
        if (employee.getId() == 0) {
            String employeeId = employee.getEmployeeId();
            Employee e = companyInfoService.getEmployeeByEId(employeeId);
            if (e != null)
                return "input-error";
        }
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
        return "input-success";
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
