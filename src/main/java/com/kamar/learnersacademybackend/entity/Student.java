package com.kamar.learnersacademybackend.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId")
    @OrderColumn
    private int studentId;

    @Column(name = "studentName")
    private String studentName;

    @Column(name = "studentPhone")
    private String studentPhone;

    @Column(name = "studentAddress")
    private String studentAddress;

    @ManyToOne
    @JoinTable(name = "student_classes",
            joinColumns = {@JoinColumn(name = "studentId")},
            inverseJoinColumns = {@JoinColumn(name = "classId")})
    private Class classes ;

    public Student() {
    }

    public Student(String studentName, String studentPhone, String studentAddress) {
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Class getClasses() {
        return classes;
    }

    public void setClasses(Class classes) {
        this.classes = classes;
    }
}
