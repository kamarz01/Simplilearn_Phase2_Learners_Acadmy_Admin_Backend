package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Subject;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.service.Impl.SubjectServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TeacherServlet", value = "/teacher")
public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teacherList = new TeacherServiceImpl().getAllTeachers();
        request.setAttribute("teacherList",teacherList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("teachers.jsp");
        dispatcher.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method != null && method.equals("delete")) {
            doDelete(request, response);
        }else if (method != null && method.equals("put")) {
            doPut(request, response);
        }else{
            String name = request.getParameter("teacherName");
            String phone = request.getParameter("teacherPhone");
            String address = request.getParameter("teacherAddress");
            Teacher teacher = new Teacher(name,phone,address);
            new TeacherServiceImpl().createTeacher(teacher);;
            doGet(request,response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        new TeacherServiceImpl().removeTeacher(teacherId);
        doGet(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("teacherName");
        String phone = request.getParameter("teacherPhone");
        String address = request.getParameter("teacherAddress");
        String id = request.getParameter("teacherId");
        Teacher teacher = new Teacher(name,phone,address);
        teacher.setTeacherId(Integer.parseInt(id));
        new TeacherServiceImpl().updateTeacher(teacher);;
        doGet(request,response);
    }
}
