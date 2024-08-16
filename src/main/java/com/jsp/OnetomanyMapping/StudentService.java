package com.jsp.OnetomanyMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getStudentsByCourseId(Long courseId, Pageable pageable) {
        return studentRepository.findByCourseId(courseId, pageable);
    }

    public Optional<Student> getStudentByIdAndCourseId(Long studentId, Long courseId) {
        return studentRepository.findById(studentId).filter(student -> student.getCourse().getId().equals(courseId));
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
