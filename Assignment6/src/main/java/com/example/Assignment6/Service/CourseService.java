package com.example.Assignment6.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Assignment6.Model.Course_model;
import com.example.Assignment6.Repository.CourseRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public List<Course_model> getAllCourses() {
        return courseRepo.findAll();
    }

    public String addCourse(Course_model course) {
        courseRepo.save(course);
        System.out.println("Course added successfully");
        return "Course added successfully";
    }

    public Course_model getCourseById(Long id) {
        Course_model course = courseRepo.findById(id).orElse(null);
        if (course != null) {
            System.out.println(course.toString());
        } else {
            System.out.println("Course not found with id: " + id);
        }
        return course;
    }

    public String deleteCourse(Long id) {
        courseRepo.deleteById(id);
        System.out.println("Course deleted successfully");
        return "Course deleted successfully";
    }

    public Course_model updateCourse(Long id, Course_model course) {
        Course_model existingCourse = courseRepo.findById(id).orElse(null);
        if (existingCourse != null) {
            existingCourse.setCourseName(course.getCourseName());
            courseRepo.save(existingCourse);
        }
        return course;
    }

    public Course_model updateCourseInstructor(Long id, String instructor) {
        Course_model existingCourse = courseRepo.findById(id).orElse(null);
        if (existingCourse != null) {
            existingCourse.setInstructor(instructor);
            courseRepo.save(existingCourse);
        }
        return existingCourse;
    }

    public List<Map<String, Object>> getCoursesWithStudentCount() {
        List<Object[]> result = courseRepo.getCoursesWithStudentCount();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : result) {
            Course_model course = (Course_model) row[0];
            Long studentCount = (Long) row[1];
            response.add(Map.of(
                    "course", course,
                    "studentCount", studentCount));
        }
        return response;
    }

    public List<Course_model> topnCourses(Integer n) {
        return courseRepo.topncourses(n);
    }

}
