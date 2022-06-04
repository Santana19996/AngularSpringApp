package com.example.amigostutorialangularspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Employee extends JpaRepository<Employee,Long> {
}
