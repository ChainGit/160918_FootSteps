package com.tgweb.springmvc.entities;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Repository
public class Employee {

    private int id;

    private String name;

    private int gender;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", salary=" + salary +
                ", birth=" + birth +
                ", department=" + department +
                '}';
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Employee(int id, String name, int gender, float salary, Date birth, Department department) {
        this.id = id;
        this.name = name;

        this.gender = gender;
        this.salary = salary;
        this.birth = birth;
        this.department = department;
    }

    public Employee(int id, String name, float salary, Date birth, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.birth = birth;
        this.department = department;
    }

    public float getSalary() {

        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @NumberFormat(pattern = "#,###,###.#")
    @DecimalMax("10000")
    @DecimalMin("1000")
    private float salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private Date birth;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Employee(int id, String name, Date birth, Department department) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.department = department;
    }

    private Department department;

    public Employee() {
    }

    public Employee(int id, String name, Department department) {

        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
