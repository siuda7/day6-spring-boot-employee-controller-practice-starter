package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "E1", 10, Gender.MALE, 5000.0));
        employees.add(new Employee(2, "E2", 20, Gender.FEMALE, 15000.0));
        employees.add(new Employee(3, "E3", 30, Gender.MALE, 35000.0));
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee getEmployeeById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getByGender(Gender gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .toList();
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployee(Integer id, Integer age, Double salary) {
        Employee updatedEmployee = employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElse(null);
        updatedEmployee.setAge(age);
        updatedEmployee.setSalary(salary);
        return updatedEmployee;
    }

    public Integer deleteEmployee(Integer id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (employee != null) {
            employees.remove(employee);
            return id;
        } else {
            return null;
        }
    }

    public List<Employee> getByPage(Integer startingPage, Integer pageSize) {
        return employees.stream()
                .skip((startingPage - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
