package net.javaguides.sms.service;

import java.util.List;
import java.util.Optional;

import net.javaguides.sms.entity.Score;
import net.javaguides.sms.entity.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Optional<Student> getStudentById(Long id);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);
    
    List<Score> getScoresByStudentId(Long studentId); // Thêm phương thức này
    void addScoreToStudent(Long studentId, Score score); // Bổ sung nếu cần
}
