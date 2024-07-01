package net.javaguides.sms.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ScoreForm {

    @NotNull
    private Long studentId;

    @NotEmpty
    private String subject;

    @NotNull
    private Double score;

    // Getters and setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
