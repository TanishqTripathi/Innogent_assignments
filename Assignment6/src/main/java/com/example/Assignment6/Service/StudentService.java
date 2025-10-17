package com.example.Assignment6.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Assignment6.Model.Course_model;
import com.example.Assignment6.Model.Student_model;
import com.example.Assignment6.Repository.CourseRepo;
import com.example.Assignment6.Repository.Studentrepo;

@Service
public class StudentService {

    @Autowired
    private Studentrepo studentrepo;

    @Autowired
    private CourseRepo courserepo;

    public List<Student_model> getAllStudents() {
        return studentrepo.findAll();
    }

    public Student_model addStudent(Student_model student, Set<String> courseNames) {
        Set<Course_model> courses = new HashSet<>();

        for (String courseName : courseNames) {
            Course_model course = courserepo.findByCourseName(courseName);
            if (course == null) {
                throw new RuntimeException("Course not found: " + courseName);
            }
            courses.add(course);
            course.getStudents().add(student); // maintain bidirectional link
        }

        student.setCourses(courses);
        studentrepo.save(student);

        System.out.println("Student added successfully");
        return student;
    }

    public Student_model updateStudent(Long id, Student_model student) {
        Student_model existingStudent = studentrepo.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            studentrepo.save(existingStudent);
        }
        return student;
    }

    public Student_model getstudentbyId(Long id) {
        Student_model student = studentrepo.findById(id).orElse(null);
        if (student != null) {
            System.out.println(student.toString());
        } else {
            System.out.println("Student not found with id: " + id);
        }
        return student;
    }

    public void deleteStudent(Long id) {
        studentrepo.deleteById(id);
        System.out.println("Student deleted successfully");
    }

    // public void findByCourseName(String courseName) {
    // List<Student_model> students = studentrepo.findByCourseName(courseName);
    // if (students != null && !students.isEmpty()) {
    // students.forEach(student -> System.out.println(student.toString()));
    // } else {
    // System.out.println("No students found enrolled in course: " + courseName);
    // }
    // }

    public Student_model getStudentbyemail(String email) {
        Student_model student = studentrepo.findByEmail(email);
        if (student != null) {
            System.out.println(student.toString());
        } else {
            System.out.println("Student not found with email: " + email);
        }
        return student;
    }

    public List<Student_model> getAllStudentsWithCourses() {
        return studentrepo.getAllStudent_with_courses();
    }

    public Integer getStudentCountForCourse(String courseName) {
        return studentrepo.student_count_for_courses(courseName);
    }

    public List<Student_model> findByCourse(String courseName) {
        return studentrepo.findbycourse(courseName);
    }

    public List<Student_model> findStudentsWithNoCourses() {
        return studentrepo.findstudentwithnocourses();
    }

    public List<Student_model> getstudentbycity(String city) {
        return studentrepo.findAllByCity(city);
    }

}
