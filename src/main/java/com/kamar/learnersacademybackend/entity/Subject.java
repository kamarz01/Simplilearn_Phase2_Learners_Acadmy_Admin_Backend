package com.kamar.learnersacademybackend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectId")
    @OrderColumn
    private int subjectId;

    @Column(name = "subjectName")
    private String subjectName;

    @Column(name = "subjectCode")
    private String subjectCode;

    @Column(name = "subjectDescription")
    private String subjectDescription;

    @ManyToMany(mappedBy = "subjects")
    private List<Class> classes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "teacher_subject",
            joinColumns = {@JoinColumn(name = "subjectId")},
            inverseJoinColumns = {@JoinColumn(name = "teacherId")})
    private List<Teacher> teachers = new ArrayList<>();

    public Subject() {
    }

    public Subject(String subjectName, String subjectCode, String subjectDescription) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectDescription = subjectDescription;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
