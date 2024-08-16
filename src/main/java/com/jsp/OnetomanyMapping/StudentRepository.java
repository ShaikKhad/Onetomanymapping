package com.jsp.OnetomanyMapping;






import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findByCourseId(Long courseId, Pageable pageable);
}

