package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.entity.Teacher;
import com.kamar.learnersacademybackend.service.Impl.StudentServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.TeacherServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = new StudentServiceImpl().getAllStudents();
        request.setAttribute("studentList",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students.jsp");
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
            String name = request.getParameter("studentName");
            String phone = request.getParameter("studentPhone");
            String address = request.getParameter("studentAddress");
            Student student = new Student(name,phone,address);
            new StudentServiceImpl().createStudent(student);;
            doGet(request,response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        new StudentServiceImpl().removeStudent(studentId);
        doGet(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("studentName");
        String phone = request.getParameter("studentPhone");
        String address = request.getParameter("studentAddress");
        String id = request.getParameter("studentId");
        Student student = new Student(name,phone,address);
        student.setStudentId(Integer.parseInt(id));
        new StudentServiceImpl().updateStudent(student);;
        doGet(request,response);
    }
}
