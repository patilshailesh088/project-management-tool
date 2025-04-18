package com.pisyst.pmt.services.Impl;

import com.pisyst.pmt.entities.Employee;
import com.pisyst.pmt.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // This tells JUnit to enable Mockito features
public class EmployeeServiceImplTest {

    @Mock //Mock the repository that the service depends on,@Mock tells Mockito to create a fake EmployeeRepository
    private EmployeeRepository employeeRepository;

    @InjectMocks//Inject the mock into the service we're testing, @InjectMocks tells Mockito to create EmployeeServiceImpl and inject the fake employeeRepository into it.
    private EmployeeServiceImpl employeeServiceImpl;

    //Test case 1: When employee is found
    @Test
    void testGetEmployeeById_WhenEmployeeExists() {
        Long id = 1L;
        Employee mockEmployee = new Employee();
        mockEmployee.setId(id);
        mockEmployee.setFirstName("John");

        //When repository is called, return this fake employee
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));

        // Actual
        Employee result = employeeServiceImpl.getEmployeeByEmpId(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("John", result.getFirstName());
    }

}
