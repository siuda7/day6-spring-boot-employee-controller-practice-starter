package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}