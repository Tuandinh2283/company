package net.javaguides.sms.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_code", nullable = false, unique = true)
    private String studentCode;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date", nullable = false)
    private LocalDate dob;

    @Column(name = "class_name", nullable = false)
    private String studentClass;

    @Column(name = "course_year", nullable = false)
    private Integer course;

    @Column(name = "process_score")
    private Double processScore;

    @Column(name = "component_score")
    private Double componentScore;

    @Column(name = "total_score")
    private Double totalScore;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Score> scores;

    // Constructors, getters, and setters...

    // Constructors
    public Student() {
    }

    public Student(String studentCode, String studentName, String gender, LocalDate dob, String studentClass, Integer course) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.gender = gender;
        this.dob = dob;
        this.studentClass = studentClass;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Double getProcessScore() {
        return processScore;
    }

    public void setProcessScore(Double processScore) {
        this.processScore = processScore;
        calculateTotalScore();
    }

    public Double getComponentScore() {
        return componentScore;
    }

    public void setComponentScore(Double componentScore) {
        this.componentScore = componentScore;
        calculateTotalScore();
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void calculateTotalScore() {
        if (this.processScore != null && this.componentScore != null) {
            this.totalScore = this.processScore + this.componentScore;
        }
    }

    public boolean getPass() {
        return this.totalScore >= 4.0;
    }
    public boolean isPassed() {
        return this.totalScore != null && this.totalScore >= 4.0;
    }

}
