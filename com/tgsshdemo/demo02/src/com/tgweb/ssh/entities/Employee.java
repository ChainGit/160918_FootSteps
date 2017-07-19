package com.tgweb.ssh.entities;

import java.sql.Date;

/**
 * Created by Chain on 2017/7/17.
 */
public class Employee {
    private long id;
    private String employeeId;
    private String employeeName;
    private String employeeEmail;
    private Byte employeeAge;
    private Date employeeBirth;
    private Date employeeCreateTime;

    private Department department;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("id=").append(id);
        sb.append(", employeeId='").append(employeeId).append('\'');
        sb.append(", employeeName='").append(employeeName).append('\'');
        sb.append(", employeeEmail='").append(employeeEmail).append('\'');
        sb.append(", employeeAge=").append(employeeAge);
        sb.append(", employeeBirth=").append(employeeBirth);
        sb.append(", employeeCreateTime=").append(employeeCreateTime);
        sb.append(", department=").append(department);
        sb.append('}');
        return sb.toString();
    }

    public Employee() {
    }

    public Employee(long id, String employeeId, String employeeName, String employeeEmail, Byte employeeAge, Date employeeBirth, Date employeeCreateTime, Department department) {

        this.id = id;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeAge = employeeAge;
        this.employeeBirth = employeeBirth;
        this.employeeCreateTime = employeeCreateTime;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Byte getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Byte employeeAge) {
        this.employeeAge = employeeAge;
    }

    public Date getEmployeeBirth() {
        return employeeBirth;
    }

    public void setEmployeeBirth(Date employeeBirth) {
        this.employeeBirth = employeeBirth;
    }

    public Date getEmployeeCreateTime() {
        return employeeCreateTime;
    }

    public void setEmployeeCreateTime(Date employeeCreateTime) {
        this.employeeCreateTime = employeeCreateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (employeeId != null ? !employeeId.equals(employee.employeeId) : employee.employeeId != null) return false;
        if (employeeName != null ? !employeeName.equals(employee.employeeName) : employee.employeeName != null)
            return false;
        if (employeeEmail != null ? !employeeEmail.equals(employee.employeeEmail) : employee.employeeEmail != null)
            return false;
        if (employeeAge != null ? !employeeAge.equals(employee.employeeAge) : employee.employeeAge != null)
            return false;
        if (employeeBirth != null ? !employeeBirth.equals(employee.employeeBirth) : employee.employeeBirth != null)
            return false;
        if (employeeCreateTime != null ? !employeeCreateTime.equals(employee.employeeCreateTime) : employee.employeeCreateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (employeeEmail != null ? employeeEmail.hashCode() : 0);
        result = 31 * result + (employeeAge != null ? employeeAge.hashCode() : 0);
        result = 31 * result + (employeeBirth != null ? employeeBirth.hashCode() : 0);
        result = 31 * result + (employeeCreateTime != null ? employeeCreateTime.hashCode() : 0);
        return result;
    }
}
