package com.kamar.learnersacademybackend.service;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.entity.Teacher;
import java.util.List;

public interface TeacherService {
    public void createTeacher(Teacher teacher);
    public Teacher getTeacherById(int teacherId);
    public List<Teacher> getAllTeachers();
    public List<Teacher> getAllTeachersWithSubjects();
    public List<Teacher> getAllTeachersNotAssigned();
    public void updateTeacher(Teacher teacher);
    public void removeTeacher(int teacherId);
    public void removeTeacherFromClass(int teacherId);

}
