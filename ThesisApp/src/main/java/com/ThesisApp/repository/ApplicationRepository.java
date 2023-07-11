package com.ThesisApp.repository;

import com.ThesisApp.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findBySubjectId(Long subjectId);


    Application findByStudentIdAndSubjectId(Long studentId , Long subjectId);


}
