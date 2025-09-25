package com.jai.jpa.Integration;

import com.jai.jpa.entity.Employee;
import com.jai.jpa.repos.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase  // Uses H2 in-memory DB
class EmployeeControllerSaveIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testAddEmployeeIntegration() throws Exception {

        // Perform POST request
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane\",\"role\":\"Tester\",\"salary\":50000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane"))
                .andExpect(jsonPath("$.role").value("Tester"))
                .andExpect(jsonPath("$.salary").value(50000));

        // Verify saved in H2 DB
        Employee savedEmployee = employeeRepository.findByName("Jane");
        assert savedEmployee != null;
        assert savedEmployee.getRole().equals("Tester");
        assert savedEmployee.getSalary() == 50000;
    }
}
