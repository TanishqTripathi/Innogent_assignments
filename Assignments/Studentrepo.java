package com.example.Assignment6.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Assignment6.Model.Student_model;

public interface Studentrepo extends JpaRepository<Student_model, Long> {

}
