package com.pisyst.pmt.repositories;

import com.pisyst.pmt.entities.EmployeeDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDocumentRepository extends JpaRepository<EmployeeDocuments, Long> {

}
