package com.pisyst.pmt.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String email;
    private String phoneNumber;
    private LocalDate doj;
    private LocalDate dob;
    private String status;
    private String nationality;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String country;
    private String department;
    private String employeeType;
    private String workLocation;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;
    private String emergencyContactPersonName;
    private String emergencyContactPersonPhoneNumber;

}
