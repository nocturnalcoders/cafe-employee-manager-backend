package com.ms.data.master.account.respository;

import com.ms.data.master.account.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);
    List<Employee> findByCafeName(String cafeName);
    Boolean existsByEmailAddress(String emailAddress);
    List<Employee> findByCafeId(Long cafeId);
    int countByCafeId(Long cafeId);
}
