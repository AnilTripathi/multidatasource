package com.secur.note.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secur.note.myql.entity.Student;
import com.secur.note.myql.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository repository;

    public Student findStudentById(Long id){
        return repository.findById(id).orElse(null);
    }
}
