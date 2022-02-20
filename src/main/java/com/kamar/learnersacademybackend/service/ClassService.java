package com.kamar.learnersacademybackend.service;

import java.util.List;
import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.dto.ClassDTO;

public interface ClassService {
    public void createClass(Class classes);
    public Class getClassById(int classId);
    public List<Class> getAllClasses();
    public  List<ClassDTO> getAllClassesWithAllData();
    public List<Class> getAllClassesWithSubjects();
    public List<Class> getAllClassesWithTeachers();
    public List<Class> getAllClassesWithStudents();
    public void addStudentToClass(int classId,Student student);
    public void addTeacherToClass(int classId, Teacher teacher);
    public void updateClass (Class classes);
    public void removeClass(int classId);
}
