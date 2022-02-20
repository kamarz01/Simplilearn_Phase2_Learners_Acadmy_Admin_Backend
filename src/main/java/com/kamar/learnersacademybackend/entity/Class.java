package com.kamar.learnersacademybackend.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classId")
    @OrderColumn
    private int classId;

    @Column(name = "className")
    private String className;

    @Column(name = "classYear")
    private int classYear;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_classes",
            joinColumns = {@JoinColumn(name = "classId")},
            inverseJoinColumns = {@JoinColumn(name = "studentId")})
    private List<Student> students = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subject_classes",
            joinColumns = {@JoinColumn(name = "classId")},
            inverseJoinColumns = {@JoinColumn(name = "subjectId")})
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_classes",
            joinColumns = {@JoinColumn(name = "classId")},
            inverseJoinColumns = {@JoinColumn(name = "teacherId")})
    private List<Teacher> teachers = new ArrayList<>();

    public Class() {
    }

    public Class(String className, int classYear) {
        this.className = className;
        this.classYear = classYear;
    }

    public Class(String className, int classYear, List<Student> students, List<Subject> subjects, List<Teacher> teachers) {
        this.className = className;
        this.classYear = classYear;
        this.students = students;
        this.subjects = subjects;
        this.teachers = teachers;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
