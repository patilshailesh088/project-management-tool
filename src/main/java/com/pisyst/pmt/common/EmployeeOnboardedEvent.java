package com.pisyst.pmt.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class EmployeeOnboardedEvent {

    private String employeeName;
    private String employeeEmail;
    private  String department;
    private LocalDate doj;
//    private String hrEmail;
    private String managerEmail;

}
