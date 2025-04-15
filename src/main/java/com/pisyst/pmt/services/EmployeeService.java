package com.pisyst.pmt.services;

import com.pisyst.pmt.dtos.EmployeeDTO;
import com.pisyst.pmt.entities.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    String onboardEmployee(EmployeeDTO employeeDTO, List<MultipartFile> files);

    Employee getEmployeeByEmpId(Long empId);

    List<Employee> getAllEmployees();

    String updateEmployee(Long empId, EmployeeDTO employee);
}
