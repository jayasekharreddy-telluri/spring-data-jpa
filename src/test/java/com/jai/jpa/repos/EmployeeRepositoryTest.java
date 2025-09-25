package com.jai.jpa.repos;


import com.jai.jpa.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // starts only JPA-related context
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveAndFindByName() {
        // given
        Employee employee = new Employee(null, "John", "Developer", 60000);
        employeeRepository.save(employee);

        // when
        Employee found = employeeRepository.findByName("John");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getRole()).isEqualTo("Developer");
    }
}
