package com.pisyst.pmt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pisyst.pmt.dtos.EmployeeDTO;
import com.pisyst.pmt.entities.Employee;
import com.pisyst.pmt.services.Impl.EmployeeServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeOnboardController {

    @Autowired
    private EmployeeServiceImpl employeeService;

//    @Autowired
//    private final ObjectMapper objectMapper;
//    public EmployeeOnboardController(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    @PostMapping(value = "/onboard", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> employeeOnboarding(@ModelAttribute("empRequest") EmployeeDTO empRequest,
                                                    @RequestPart("files") List<MultipartFile> files) {
     // log.info("Received the Employee Onboard Request. {}", objectMapper.writeValueAsString(empRequest));
        String message = employeeService.onboardEmployee(empRequest, files);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{empId}")
    public ResponseEntity<Employee> employeeById(@PathVariable Long empId) {
        Employee employee = employeeService.getEmployeeByEmpId(empId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> employeeById() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PatchMapping("/updateEmployee/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeDTO employee) {
        String msg = employeeService.updateEmployee(empId, employee);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
