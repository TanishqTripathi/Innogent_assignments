package com.example.Assignment6.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Assignment6.Model.Student_model;
import com.example.Assignment6.Repository.Studentrepo;

@Service
public class StudentService {

    @Autowired
    private Studentrepo studentrepo;

    public List<Student_model> getAllStudents() {
        return studentrepo.findAll();
    }

    public StudentService addStudent(Student_model student) {
        studentrepo.save(student);
        return this;
    }

    public StudentService updateStudent(Long id, Student_model student) {
        Student_model existingStudent = studentrepo.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            studentrepo.save(existingStudent);
        }
        return this;
    }
}
