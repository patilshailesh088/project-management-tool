package com.pisyst.pmt.services;

import com.pisyst.pmt.dtos.EmployeeDTO;
import com.pisyst.pmt.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    String onboardEmployee(EmployeeDTO employeeDTO, List<MultipartFile> files);

    Employee getEmployeeByEmpId(Long empId);

    Page<Employee> getAllEmployeesWithPagination(int page, int size, String sortBy);

    String updateEmployee(Long empId, EmployeeDTO employee);
}
