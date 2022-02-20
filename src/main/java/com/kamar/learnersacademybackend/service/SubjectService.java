package com.kamar.learnersacademybackend.service;

import com.kamar.learnersacademybackend.entity.Subject;
import java.util.List;

public interface SubjectService {
    public void createSubject(Subject subject);
    public Subject getSubjectById(int subjectId);
    public List<Subject> getAllSubject();
    public void updateSubject(Subject subject);
    public void removeSubject(int subjectId);
}
