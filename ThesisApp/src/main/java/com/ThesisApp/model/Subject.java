package com.ThesisApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "title")
    private String title;


    @Column(name = "description")
    private String description;

   @Column(name = "available")
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "subject")
    private List<Application> applications;

    @Transient
    private Double coursesThreshold;

    @Transient
    private Double gradeThreshold;

    public Subject() {}

    public Subject(String title, String description, Professor professor, List<Application> applications) {
        this.title = title;
        this.description = description;
        this.professor = professor;
        this.applications = applications;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public int getTotalApplications(){
        return applications.size();
    }
    public Boolean isAvailable(){
        return this.available;
    }
    public void setUnavailable(){
        this.available = false;
    }
    public void setAvailable(){
        this.available = true;
    }

    public Double getCoursesThreshold() {
        return coursesThreshold;
    }

    public void setCoursesThreshold(Double coursesThreshold) {
        this.coursesThreshold = coursesThreshold;
    }

    public Double getGradeThreshold() {
        return gradeThreshold;
    }

    public void setGradeThreshold(Double gradeThreshold) {
        this.gradeThreshold = gradeThreshold;
    }
}