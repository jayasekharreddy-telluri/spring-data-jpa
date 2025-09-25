package com.jai.jpa.controller;

import com.jai.jpa.entity.Employee;
import com.jai.jpa.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void testAddEmployee() throws Exception {
        Employee employee = new Employee(1L, "Jane", "Tester", 50000);

        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")  // Matches your controller mapping
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane\",\"role\":\"Tester\",\"salary\":50000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.role").value("Tester"))
                .andExpect(jsonPath("$.salary").value(50000));
    }

    @Test
    void testGetEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(
                        new Employee(1L, "Jane", "Tester", 50000),
                        new Employee(2L, "John", "Developer", 60000)
                ));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jane"))
                .andExpect(jsonPath("$[1].name").value("John"));
    }

    @Test
    void testGetEmployeeByName() throws Exception {
        Mockito.when(employeeService.getByName("Jane"))
                .thenReturn(new Employee(1L, "Jane", "Tester", 50000));

        mockMvc.perform(get("/employees/Jane"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.role").value("Tester"));
    }
}
