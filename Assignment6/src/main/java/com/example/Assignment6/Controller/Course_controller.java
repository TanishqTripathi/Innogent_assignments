package com.example.Assignment6.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Assignment6.Model.Course_model;
import com.example.Assignment6.Service.CourseService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/courses")
public class Course_controller {

    private final CourseService CourseService;

    public Course_controller(CourseService CourseService) {
        this.CourseService = CourseService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Course_model>> getAllCourses() {
        List<Course_model> courses = CourseService.getAllCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Map<String, Object> request) {
        Course_model course = new Course_model();
        course.setCourseName((String) request.get("course_Name"));
        course.setDescription((String) request.get("description"));
        course.setInstructor((String) request.get("instructor"));

        return ResponseEntity.ok(CourseService.addCourse(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course_model> getCourseById(@RequestParam Long id) {
        Course_model course = CourseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<String> updatecourses(@PathVariable Long id, @RequestBody Course_model course) {
        CourseService.updateCourse(id, course);
        return ResponseEntity.ok("Course updated successfully");

    }

    @PutMapping("/updateInstructor/{id}")
    public ResponseEntity<String> updateCourseInstructor(@PathVariable Long id,
            @RequestBody Map<String, String> request) {
        String instructor = request.get("instructor");
        CourseService.updateCourseInstructor(id, instructor);
        return ResponseEntity.ok("Course instructor updated successfully");
    }

    @GetMapping("/studentCount")
    public ResponseEntity<List<Map<String, Object>>> getCoursesWithStudentCount() {
        List<Map<String, Object>> coursesWithCounts = CourseService.getCoursesWithStudentCount();
        if (coursesWithCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(coursesWithCounts);
    }

    @GetMapping("/topn/{n}")
    public ResponseEntity<List<Course_model>> topnCourses(@PathVariable Integer n) {
        List<Course_model> topCourses = CourseService.topnCourses(n);
        if (topCourses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(topCourses);
    }

}
