package com.pisyst.pmt.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Employee_Details")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @Column(name="First_Name" , nullable = false, length=25)
    private String firstName;

    @Column(name="Middle_Name", length=25)
    private String middleName;

    @Column(name="Last_Name", length=25)
    private String lastName;

    @Column(name="Gender", nullable=false)
    private String gender;

    @Column(name="Email_Id", nullable=false, length=100)
    @Email(message="Invalid Email format")
    @Size(min=2, max=50)
    private String email;

    @Column(name="Phone_Number", nullable=false)
    private String phoneNumber;

    @Column(name="Date_Of_Joining")
    private LocalDate doj;

    @Column(name="Date_of_Birth")
    private LocalDate dob;

    @Column(name="Employee_Status")
    private String status;

    @Column(name="Nationality")
    private String nationality;

    @Column(name="Address")
    private String address;

    @Column(name="City", nullable = false)
    private String city;

    @Column(name="State", nullable = false)
    private String state;

    @Column(name="PinCode")
    private String pinCode;

    @Column(name="Country", nullable = false)
    private String country;

    @Column(name="Department")
    private String department;

    @Column(name="EmployeeType")
    private String employeeType;

    @Column(name="Work_Location")
    private String workLocation;

    @CreationTimestamp
    @Column(name="Create_Date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name="Modify_Date", nullable = false)
    private LocalDateTime modifyDate;

    @Column(name="Emergency_Contact_Person_Name", nullable = false)
    private String emergencyContactPersonName;

    @Column(name="Emergency_Contact_Person_Phone_Number", nullable = false)
    private String emergencyContactPersonPhoneNumber;

    //Documents;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeDocuments> documents;
}
