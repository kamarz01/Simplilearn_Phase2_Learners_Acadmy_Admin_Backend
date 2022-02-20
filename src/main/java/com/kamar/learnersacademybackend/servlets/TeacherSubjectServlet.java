package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Subject;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.SubjectServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "TeacherSubjectServlet", value = "/teacherSubject")
public class TeacherSubjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teacherList = new TeacherServiceImpl().getAllTeachersWithSubjects();
        List<Subject> subjectList = new SubjectServiceImpl().getAllSubject();
        request.setAttribute("teacherList",teacherList.stream().distinct().collect(Collectors.toList()));
        request.setAttribute("subjectList",subjectList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherSubject.jsp");
        dispatcher.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String teacherId = request.getParameter("teacherId");
        String[] subjects = request.getParameterValues("subjects");
        if (subjects != null && subjects.length > 0){
            List<Subject> subList = new ArrayList<>();
            for (String s:subjects) {
                Subject tempSubject = new SubjectServiceImpl().getSubjectById(Integer.parseInt(s));
                subList.add(tempSubject);
            }
            TeacherServiceImpl service = new TeacherServiceImpl();
            Teacher teacherToUpdate = service.getTeacherById(Integer.parseInt(teacherId));
            teacherToUpdate.setSubjects(subList);
            service.updateTeacher(teacherToUpdate);
            doGet(request,response);
        }
    }
}
