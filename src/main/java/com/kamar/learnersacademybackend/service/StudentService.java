package com.kamar.learnersacademybackend.service;

import com.kamar.learnersacademybackend.entity.Student;
import java.util.List;

public interface StudentService {
    public void createStudent(Student student);
    public Student getStudentById(int studentId);
    public List<Student> getAllStudents();
    public List<Student> getAllStudentsNotAssigned();
    public void updateStudent(Student student);
    public void removeStudent(int studentId);
    public void removeStudentFromClass(int studentId);

}
