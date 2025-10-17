package com.example.Assignment6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Assignment6.Model.Course_model;

@Repository
public interface CourseRepo extends JpaRepository<Course_model, Long> {

    Course_model findByCourseName(String courseName);

    @Query("SELECT c, COUNT(s) AS studentCount FROM Course_model c LEFT JOIN c.students s GROUP BY c")
    List<Object[]> getCoursesWithStudentCount();

    @Query("SELECT c FROM Course_model c LEFT JOIN c.students s GROUP BY c ORDER BY COUNT(s) DESC")
    List<Course_model> topncourses(Integer n);

}
