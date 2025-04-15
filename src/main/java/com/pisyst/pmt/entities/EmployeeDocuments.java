package com.pisyst.pmt.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee_Documents")
public class EmployeeDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="File_Name")
    private String fileName;

    @Column(name="File_Path")
    private String filePath;

    @Column(name="File_Type")
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "Employee_ID", nullable = false)
    @JsonBackReference
    private Employee employee;
}
