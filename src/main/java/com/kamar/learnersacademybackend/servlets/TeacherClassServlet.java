package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.TeacherServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "TeacherClassServlet", value = "/teacherClass")
public class TeacherClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Class> classList = new ClassServiceImpl().getAllClassesWithTeachers();
        List<Teacher> teacherList = new TeacherServiceImpl().getAllTeachersNotAssigned();
        request.setAttribute("classList", classList.stream().distinct().collect(Collectors.toList()));
        request.setAttribute("teacherList", teacherList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teacherClass.jsp");
        dispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method != null && method.equals("delete")) {
            doDelete(request, response);
        } else {
            String classId = request.getParameter("classId");
            String teacher = request.getParameter("teachers");
            if (teacher != null) {
                Teacher tempTeacher = new TeacherServiceImpl().getTeacherById(Integer.parseInt(teacher));
                new ClassServiceImpl().addTeacherToClass(Integer.parseInt(classId),tempTeacher);
            }
            doGet(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        new TeacherServiceImpl().removeTeacherFromClass(teacherId);
        doGet(request, response);
    }
}
