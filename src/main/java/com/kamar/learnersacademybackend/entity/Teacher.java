package com.kamar.learnersacademybackend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherId")
    @OrderColumn
    private int teacherId;

    @Column(name = "teacherName")
    private String teacherName;

    @Column(name = "teacherPhone")
    private String teacherPhone;

    @Column(name = "teacherAddress")
    private String teacherAddress;

    @ManyToMany
    @JoinTable(name = "teacher_subject",
            joinColumns = {@JoinColumn(name = "teacherId")},
            inverseJoinColumns = {@JoinColumn(name = "subjectId")})
    private List<Subject> subjects = new ArrayList<>();

    @ManyToOne
    @JoinTable(name = "teacher_classes",
            joinColumns = {@JoinColumn(name = "teacherId")},
            inverseJoinColumns = {@JoinColumn(name = "classId")})
    private Class classes;

    public Teacher() {
    }

    public Teacher(String teacherName, String teacherPhone, String teacherAddress) {
        this.teacherName = teacherName;
        this.teacherPhone = teacherPhone;
        this.teacherAddress = teacherAddress;
    }

    public Teacher(String teacherName, String teacherPhone, String teacherAddress, List<Subject> subjects) {
        this.teacherName = teacherName;
        this.teacherPhone = teacherPhone;
        this.teacherAddress = teacherAddress;
        this.subjects = subjects;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Class getClasses() {
        return classes;
    }

    public void setClasses(Class classes) {
        this.classes = classes;
    }
}
