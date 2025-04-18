package com.pisyst.pmt.services.Impl;

import com.pisyst.pmt.KafkaDetails.KafkaProducerService;
import com.pisyst.pmt.controllers.Exception.EmployeeNotFoundException;
import com.pisyst.pmt.dtos.EmployeeDTO;
import com.pisyst.pmt.entities.Employee;
import com.pisyst.pmt.entities.EmployeeDocuments;
import com.pisyst.pmt.repositories.EmployeeDocumentRepository;
import com.pisyst.pmt.repositories.EmployeeRepository;
import com.pisyst.pmt.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDocumentRepository employeeDocumentRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public String onboardEmployee(EmployeeDTO employeeRequest, List<MultipartFile> files) {

        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeRequest.getEmail());

        if (existingEmployee.isPresent()) {
            return "An employee with the email " + existingEmployee.get().getEmail() + " already exists in the system. Please verify the details and try again.";
        } else {
            Employee employee = Employee.builder()
                    .firstName(employeeRequest.getFirstName())
                    .middleName(employeeRequest.getMiddleName())
                    .lastName(employeeRequest.getLastName())
                    .gender(employeeRequest.getGender())
                    .email(employeeRequest.getEmail())
                    .phoneNumber(employeeRequest.getPhoneNumber())
                    .dob(employeeRequest.getDob())
                    .doj(employeeRequest.getDoj())
                    .status(employeeRequest.getStatus())
                    .nationality(employeeRequest.getNationality())
                    .address(employeeRequest.getAddress())
                    .city(employeeRequest.getCity())
                    .state(employeeRequest.getState())
                    .pinCode(employeeRequest.getPinCode())
                    .country(employeeRequest.getCountry())
                    .department(employeeRequest.getDepartment())
                    .employeeType(employeeRequest.getEmployeeType())
                    .workLocation(employeeRequest.getWorkLocation())
                    .emergencyContactPersonName(employeeRequest.getEmergencyContactPersonName())
                    .emergencyContactPersonPhoneNumber(employeeRequest.getEmergencyContactPersonPhoneNumber())
                    .build();

            Employee savedEmployee = employeeRepository.save(employee);

            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    String s3Url = s3Service.uploadFile(file);

                    EmployeeDocuments docs = EmployeeDocuments.builder()
                                    .fileName(file.getOriginalFilename())
                                    .fileType(file.getContentType())
                                    .filePath(s3Url)
                                    .employee(savedEmployee)
                                    .build();
                    employeeDocumentRepository.save(docs);
                }
            }

            kafkaProducerService.sendEmployeeOnboardedEvent(employeeRequest);
            log.info("Employee Onboarded Successfully.");
            return "Employee saved successfully with the Employee ID : " + employee.getId();
        }
    }

    @Override
    public Employee getEmployeeByEmpId(Long empId) {
        return employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + empId + " not found."));
    }

    @Override
    public Page<Employee> getAllEmployeesWithPagination(int page, int size, String sortBy) {

        Sort sort = sortBy.equalsIgnoreCase("desc")
                    ? Sort.by("createdDate").descending() :
                      Sort.by("createdDate").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public String updateEmployee(Long empId, EmployeeDTO employee) {
        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found with the given ID :" + empId));

        if (employee.getFirstName() != null)  {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getMiddleName() != null)  {
            existingEmployee.setMiddleName(employee.getMiddleName());
        }
        if (employee.getLastName() != null)  {
            existingEmployee.setLastName(employee.getLastName());
        }
        if (employee.getGender() != null)  {
            existingEmployee.setGender(employee.getGender());
        }
        if (employee.getEmail() != null)  {
            existingEmployee.setEmail(employee.getEmail());
        }
        if (employee.getPhoneNumber() != null)  {
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        }
        if (employee.getDoj() != null)  {
            existingEmployee.setDoj(employee.getDoj());
        }
        if (employee.getDob() != null)  {
            existingEmployee.setDob(employee.getDob());
        }
        if (employee.getStatus() != null)  {
            existingEmployee.setStatus(employee.getStatus());
        }
        if (employee.getNationality() != null)  {
            existingEmployee.setNationality(employee.getNationality());
        }
        if (employee.getAddress() != null)  {
            existingEmployee.setAddress(employee.getAddress());
        }
        if (employee.getCity() != null)  {
            existingEmployee.setCity(employee.getCity());
        }
        if (employee.getState() != null)  {
            existingEmployee.setState(employee.getState());
        }
        if (employee.getPinCode() != null)  {
            existingEmployee.setPinCode(employee.getPinCode());
        }
        if (employee.getCountry() != null)  {
            existingEmployee.setCountry(employee.getCountry());
        }
        if (employee.getDepartment() != null)  {
            existingEmployee.setDepartment(employee.getDepartment());
        }
        if (employee.getEmployeeType() != null)  {
            existingEmployee.setEmployeeType(employee.getEmployeeType());
        }
        if (employee.getWorkLocation() != null)  {
            existingEmployee.setWorkLocation(employee.getWorkLocation());
        }
        if (employee.getEmergencyContactPersonName() != null)  {
            existingEmployee.setEmergencyContactPersonName(employee.getEmergencyContactPersonName());
        }
        if (employee.getEmergencyContactPersonPhoneNumber() != null)  {
            existingEmployee.setEmergencyContactPersonPhoneNumber(employee.getEmergencyContactPersonPhoneNumber());
        }

        employeeRepository.save(existingEmployee);
        return "Employee Updated Successfully";
    }

}
