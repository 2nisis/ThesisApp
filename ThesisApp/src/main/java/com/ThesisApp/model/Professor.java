package com.ThesisApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "surname")
    private String surname;


    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "professor")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "professor")
    private List<Thesis> theses;

    public Professor() {
    }
    public Professor(String name){
        this.name = name;
        this.surname = "null";
        this.email = "null";
    }

    public Professor(String name, String surname, String email, User user, List<Subject> subjects, List<Thesis> theses) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.user = user;
        this.subjects = subjects;
        this.theses = theses;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Thesis> getTheses() {
        return theses;
    }

    public void setTheses(List<Thesis> theses) {
        this.theses = theses;
    }

    public String toString(){
        return this.name;
    }

    public void setId(long l) {
        this.id = l;
    }
}
