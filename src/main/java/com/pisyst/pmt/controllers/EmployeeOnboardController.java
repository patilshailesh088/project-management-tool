package com.pisyst.pmt.controllers;

import com.pisyst.pmt.dtos.EmployeeDTO;
import com.pisyst.pmt.entities.Employee;
import com.pisyst.pmt.services.Impl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@Tag(name="Project Management API's", description = "Project Management endpoints")
public class EmployeeOnboardController {

    @Autowired
    private EmployeeServiceImpl employeeService;

//    @Autowired
//    private final ObjectMapper objectMapper;
//    public EmployeeOnboardController(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    @Operation(summary="Onboarding Employee")
    @PostMapping(value = "/onboard", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> employeeOnboarding(@ModelAttribute("empRequest") EmployeeDTO empRequest,
                                                    @RequestPart("files") List<MultipartFile> files) {
     // log.info("Received the Employee Onboard Request. {}", objectMapper.writeValueAsString(empRequest));
        String message = employeeService.onboardEmployee(empRequest, files);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @Operation(summary="Fetch Employee by using Employee ID")
    @GetMapping("/getById/{empId}")
    public ResponseEntity<Employee> employeeById(@PathVariable Long empId) {
        Employee employee = employeeService.getEmployeeByEmpId(empId);
        return ResponseEntity.ok(employee);
    }

    @Operation(summary="Fetch all Employees")
    @GetMapping("/getEmployeesWithPagination")
    public ResponseEntity<Page<Employee>> getAllEmployeesWithPagination(@RequestParam (defaultValue = "0") int page,
                                                                        @RequestParam (defaultValue = "10") int size,
                                                                        @RequestParam (defaultValue = "desc") String sortBy) {
       org.springframework.data.domain.Page<Employee> employees = employeeService.getAllEmployeesWithPagination(page, size, sortBy);

        return ResponseEntity.ok(employees);
    }

    @Operation(summary="Update the Employee Details")
    @PatchMapping("/updateEmployee/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeDTO employee) {
        String msg = employeeService.updateEmployee(empId, employee);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
