package com.vageesh.jwt_auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vageesh.jwt_auth.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
