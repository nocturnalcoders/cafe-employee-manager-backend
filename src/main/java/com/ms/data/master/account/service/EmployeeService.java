package com.ms.data.master.account.service;

import com.ms.data.master.account.model.Cafe;
import com.ms.data.master.account.model.Employee;
import com.ms.data.master.account.model.dto.account.EmployeeDTO;
import com.ms.data.master.account.respository.CafeRepository;
import com.ms.data.master.account.respository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CafeRepository cafeRepository;

    private EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmailAddress(employee.getEmailAddress());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setStartDate(employee.getStartDate());
        employeeDTO.setCafeName(employee.getCafe() != null ? employee.getCafe().getName() : null);
        employeeDTO.setDaysWorked(Period.between(employee.getStartDate(), LocalDate.now()).getDays());
        return employeeDTO;
    }

    // Convert EmployeeDTO to Employee entity
    private Employee fromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setName(employeeDTO.getName());
        employee.setEmailAddress(employeeDTO.getEmailAddress());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());

        if (employeeDTO.getCafeName() != null) {
            Cafe cafe = cafeRepository.findByName(employeeDTO.getCafeName())
                    .orElseThrow(() -> new EntityNotFoundException("Cafe not found with name: " + employeeDTO.getCafeName()));
            employee.setCafe(cafe);
        }

        return employee;
    }

    // Get employees by cafe name, or all employees if cafe name is null
    public List<EmployeeDTO> getEmployeesByCafe(String cafeName) {
        List<Employee> employees;

        if (cafeName == null || cafeName.isEmpty()) {
            employees = employeeRepository.findAll();
        } else {
            Cafe cafe = cafeRepository.findByName(cafeName)
                    .orElseThrow(() -> new EntityNotFoundException("Cafe not found with name: " + cafeName));
            employees = employeeRepository.findByCafeId(cafe.getId());
        }

        return employees.stream()
                .map(this::toDTO)
                .sorted(Comparator.comparingInt(EmployeeDTO::getDaysWorked).reversed())
                .collect(Collectors.toList());
    }

    // Create a new employee
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByEmailAddress(employeeDTO.getEmailAddress())) {
            throw new IllegalArgumentException("Employee with this email address already exists.");
        }
        Employee employee = fromDTO(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return toDTO(savedEmployee);
    }

    // Update an existing employee
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        if (!employeeRepository.existsById(employeeDTO.getId())) {
            throw new EntityNotFoundException("Employee not found with id: " + employeeDTO.getId());
        }
        Employee employee = fromDTO(employeeDTO);
        Employee updatedEmployee = employeeRepository.save(employee);
        return toDTO(updatedEmployee);
    }

    // Delete an existing employee
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}

