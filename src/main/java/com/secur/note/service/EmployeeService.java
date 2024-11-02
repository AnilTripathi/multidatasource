package com.secur.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secur.note.pgsql.entity.Employee;
import com.secur.note.pgsql.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee findEmployeeById(Long id){
        return repository.findById(id).orElse(null);
    }
}