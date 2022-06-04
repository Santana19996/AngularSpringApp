package com.example.amigostutorialangularspring.service;

import com.example.amigostutorialangularspring.exceptions.UserNotFoundException;
import com.example.amigostutorialangularspring.models.Employee;
import com.example.amigostutorialangularspring.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployeeNameById(Employee employee, long id) {
        return employeeRepo.findById(id).map(employee1 -> {
            employee1.setName(employee.getName());

            return employeeRepo.save(employee1);
        }).orElseGet(() -> {
            employee.setId(id);
            return employeeRepo.save(employee);
        });

    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Employee updateEmployeeById(Employee employee, long id) {
        return employeeRepo.findById(id).map(employee1 -> {
            employee1.setName(employee.getName());
            employee1.setJobTitle(employee.getJobTitle());
            employee1.setPhone(employee.getPhone());
            employee1.setImageUrl(employee.getImageUrl());
            return employeeRepo.save(employee1);
        }).orElseGet(() -> {
            employee.setId(id);
            return employeeRepo.save(employee);
        });
    }

}




