package com.ThesisApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "surname")
    private String surname;

    @Column( name = "yearOfStudies")
    private Integer yearOfStudies;

    @Column( name = "averageGrade")
    private Integer averageGrade;

    @Column( name = "remainingCourses")
    private Integer remainingCourses;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "student")
    private Thesis thesis;

    @OneToMany(mappedBy = "student")
    private List<Application> applications;

    public Student() {}

    public Student(String name, String surname, Integer yearOfStudies, Integer averageGrade, Integer remainingCourses, User user, Thesis thesis, List<Application> applications) {
        this.name = name;
        this.surname = surname;
        this.yearOfStudies = yearOfStudies;
        this.averageGrade = averageGrade;
        this.remainingCourses = remainingCourses;
        this.user = user;
        this.thesis = thesis;
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(Integer yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }

    public Integer getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Integer averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Integer getRemainingCourses() {
        return remainingCourses;
    }

    public void setRemainingCourses(Integer remainingCourses) {
        this.remainingCourses = remainingCourses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }


    public void setId(long l) {
        this.id = l;
    }
}
