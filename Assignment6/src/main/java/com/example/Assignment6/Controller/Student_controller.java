package com.example.Assignment6.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.Assignment6.Model.Student_model;
// import com.example.Assignment6.Repository.Studentrepo;
import com.example.Assignment6.Service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Student_controller {

    @Autowired
    private final StudentService studentService;

    public Student_controller(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student_model> getStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student_model getStudentById(@PathVariable Long id) {
        return studentService.getstudentbyId(id);
    }

    @PostMapping("/add")
    public Student_model addStudent(@RequestBody Map<String, Object> request) {
        Student_model student = new Student_model();
        student.setName((String) request.get("name"));
        student.setEmail((String) request.get("email"));
        student.setAddress((String) request.get("address"));
        student.setCity((String) request.get("city"));

        List<String> courseNameList = (List<String>) request.get("courseNames");
        Set<String> courseNames = courseNameList != null ? new HashSet<>(courseNameList)
                : new HashSet<>(courseNameList);

        return studentService.addStudent(student, courseNames);
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable long id, @RequestBody Student_model student) {
        studentService.updateStudent(id, student);
        return "Student updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @GetMapping("/students/email/{email}")
    public ResponseEntity<Student_model> getStudentByEmail(@PathVariable String email) {
        Student_model student = studentService.getStudentbyemail(email);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allStudentsWithCourses")
    public List<Student_model> getAllStudentsWithCourses() {
        return studentService.getAllStudentsWithCourses();
    }

    @GetMapping("/studentCountForCourse/{courseName}")
    public Integer getStudentCountForCourse(@PathVariable String courseName) {
        return studentService.getStudentCountForCourse(courseName);
    }

    @GetMapping("/studentsByCourse/{courseName}")
    public List<Student_model> getStudentsByCourse(@PathVariable String courseName) {
        return studentService.findByCourse(courseName);
    }

    @GetMapping("/studentsWithNoCourses")
    public List<Student_model> getStudentsWithNoCourses() {
        return studentService.findStudentsWithNoCourses();
    }

    @GetMapping("/student/{city}")
    public List<Student_model> getStudentsByCity(@PathVariable String city) {
        return studentService.getstudentbycity(city);
    }

    // @GetMapping("/studentsWithCourses")
    // public List<Student_model> getStudentsWithCourses() {
    // return studentService.getAllStudentsWithCourses();
    // }
}
