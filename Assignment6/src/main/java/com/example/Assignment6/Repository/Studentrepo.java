package com.example.Assignment6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Assignment6.Model.Student_model;

@Repository
public interface Studentrepo extends JpaRepository<Student_model, Long> {

    // @Query("SELECT s FROM Student_model s JOIN s.courses c WHERE c.courseName
    // =:courseName")
    // List<Student_model> findByCourseName(String Course_Name);

    @Query("SELECT s FROM Student_model s WHERE LOWER(s.email) = LOWER(:email)")
    Student_model findByEmail(String email);

    @Query("SELECT DISTINCT s FROM Student_model s LEFT JOIN FETCH s.courses")
    List<Student_model> getAllStudent_with_courses();

    @Query("SELECT COUNT(s) FROM Student_model s JOIN s.courses c WHERE c.courseName = :courseName")
    Integer student_count_for_courses(String courseName);

    @Query("SELECT s FROM Student_model s JOIN s.courses c WHERE c.courseName = :courseName")
    List<Student_model> findbycourse(String courseName);

    @Query("SELECT s FROM Student_model s WHERE s.courses IS EMPTY")
    List<Student_model> findstudentwithnocourses();

    @Query("SELECT s FROM Student_model s WHERE s.city = :city")
    List<Student_model> findAllByCity(String city);

}
