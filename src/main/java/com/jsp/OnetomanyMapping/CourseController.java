package com.jsp.OnetomanyMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    
    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {
        Pageable pageable = PageRequest.of(page, size, sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Course> courses = courseService.getAllCourses(pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @RequestBody Course courseDetails) {
        return courseService.getCourseById(courseId)
                .map(course -> {
                    course.setName(courseDetails.getName());
                    Course updatedCourse = courseService.saveCourse(course);
                    return ResponseEntity.ok(updatedCourse);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId)
                .map(course -> {
                    courseService.deleteCourse(courseId);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @GetMapping("/{courseId}/students")
    public ResponseEntity<Page<Student>> getStudentsByCourseId(
            @PathVariable Long courseId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam String sortDir) {
        Pageable pageable = PageRequest.of(page, size, sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Student> students = studentService.getStudentsByCourseId(courseId, pageable);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long courseId, @PathVariable Long studentId) {
        return studentService.getStudentByIdAndCourseId(studentId, courseId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{courseId}/students")
    public ResponseEntity<Student> addStudentToCourse(@PathVariable Long courseId, @RequestBody Student student) {
        return courseService.getCourseById(courseId)
                .map(course -> {
                    student.setCourse(course);
                    Student savedStudent = studentService.saveStudent(student);
                    return ResponseEntity.ok(savedStudent);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long courseId, @PathVariable Long studentId, @RequestBody Student studentDetails) {
        return studentService.getStudentByIdAndCourseId(studentId, courseId)
                .map(student -> {
                    student.setName(studentDetails.getName());
                    Student updatedStudent = studentService.saveStudent(student);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return studentService.getStudentByIdAndCourseId(studentId, courseId)
                .map(student -> {
                    studentService.deleteStudent(studentId);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
