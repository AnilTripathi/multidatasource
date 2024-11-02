package com.secur.note.myql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secur.note.myql.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
