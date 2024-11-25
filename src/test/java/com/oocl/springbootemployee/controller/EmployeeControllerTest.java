package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class EmployeeControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JacksonTester<List<Employee>> employeesJacksonTester;

    @Autowired
    private JacksonTester<Employee> employeeJacksonTester;

    @Test
    void should_return_employees_when_get_all_given_employees() throws Exception{

        List<Employee> expectedEmployees = employeeRepository.getAll();

        //Given
        String employeesResponseString = client.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Employee> employees = employeesJacksonTester.parseObject(employeesResponseString);
        assertEquals(expectedEmployees, employees);
        //When

        //Then

    }

    @Test
    void should_return_employee_when_get_by_id_given_id() throws Exception{

        //Given
        Employee expectedEmployee = employeeRepository.getEmployeeById(2);
        String employeeResponseString = client.perform(MockMvcRequestBuilders.get("/employees/" + expectedEmployee.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Employee employee = employeeJacksonTester.parseObject(employeeResponseString);
        assertEquals(expectedEmployee, employee);

        //When


        //Then


    }

    @Test
    void should_return_male_when_get_male_given_employees() throws Exception{

        //Given
        List<Employee> expectedEmployees = employeeRepository.getByGender(Gender.MALE);

        //Given
        String employeesResponseString = client.perform(MockMvcRequestBuilders.get("/employees")
                        .param("gender", Gender.MALE.name()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Employee> employees = employeesJacksonTester.parseObject(employeesResponseString);
        assertEquals(expectedEmployees, employees);


        //When


        //Then


    }


}
