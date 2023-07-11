package com.ThesisApp.model;

import javax.persistence.*;

@Entity
@Table(name = "theses")
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "implementationGrade")
    private Double implementationGrade;

    @Column(name = "reportGrade")
    private Double reportGrade;

    @Column(name = "presentationGrade")
    private Double presentationGrade;

    @Column(name = "OverallGrade")
    private Double OverallGrade;

    public Thesis() {
    }

    public Thesis(Student student, Subject subject, Professor professor) {
        this.student = student;
        this.subject = subject;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Double getImplementationGrade() {
        return implementationGrade;
    }

    public void setImplementationGrade(Double implementationGrade) {
        this.implementationGrade = implementationGrade;
    }

    public Double getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(Double reportGrade) {
        this.reportGrade = reportGrade;
    }

    public Double getPresentationGrade() {
        return presentationGrade;
    }

    public void setPresentationGrade(Double presentationGrade) {
        this.presentationGrade = presentationGrade;
    }

    public Double getOverallGrade() {
        return OverallGrade;
    }

    public void setOverallGrade(Double overallGrade) {
        OverallGrade = overallGrade;
    }
}
