package com.tgweb.ssh.entities;

import java.util.Set;

/**
 * Created by Chain on 2017/7/17.
 */
public class Department {
    private long id;
    private String departmentId;
    private String departmentName;

    private Set<Employee> employeeSet;

    public Department(long id, String departmentId, String departmentName) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    @Override

    public String toString() {
        final StringBuffer sb = new StringBuffer("Department{");
        sb.append("id=").append(id);
        sb.append(", departmentId='").append(departmentId).append('\'');
        sb.append(", departmentName='").append(departmentName).append('\'');
        sb.append(", employeeSet=").append(employeeSet == null ? employeeSet : employeeSet.size());
        sb.append('}');
        return sb.toString();
    }

    public Department() {
    }

    public Department(long id, String departmentId, String departmentName, Set<Employee> employeeSet) {

        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeSet = employeeSet;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != that.id) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }
}
