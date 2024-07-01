package net.javaguides.sms.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.sms.entity.Score;
import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @Override
    public Student saveStudent(Student student) {
        if (student.getDob() == null) {
            student.setDob(LocalDate.now()); // Set ngày hiện tại nếu không có ngày sinh được cung cấp
        }
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(student.getId());
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setStudentCode(student.getStudentCode());
            existingStudent.setStudentName(student.getStudentName());
            existingStudent.setGender(student.getGender());
            existingStudent.setDob(student.getDob());
            existingStudent.setStudentClass(student.getStudentClass());
            existingStudent.setCourse(student.getCourse());
            existingStudent.setProcessScore(student.getProcessScore());
            existingStudent.setComponentScore(student.getComponentScore());
            existingStudent.calculateTotalScore(); // tính lại totalScore sau khi cập nhật processScore và componentScore
            return studentRepository.save(existingStudent);
        } else {
            // throw exception or handle as needed
            return null; // hoặc trả về null để xử lý ở phía controller
        }
    }

    @Override
    public void deleteStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        studentOptional.ifPresent(student -> studentRepository.deleteById(id));
    }

	@Override
	public void addScoreToStudent(Long studentId, Score score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Score> getScoresByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
