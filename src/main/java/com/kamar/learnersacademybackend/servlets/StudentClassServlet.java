package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "StudentClassServlet", value = "/studentClass")
public class StudentClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Class> classList = new ClassServiceImpl().getAllClassesWithStudents();
        List<Student> studentList = new StudentServiceImpl().getAllStudentsNotAssigned();
        request.setAttribute("classList", classList.stream().distinct().collect(Collectors.toList()));
        request.setAttribute("studentList", studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentClass.jsp");
        dispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method != null && method.equals("delete")) {
            doDelete(request, response);
        } else {
            String classId = request.getParameter("classId");
            String student = request.getParameter("students");
            if (student != null) {
                Student tempStudent = new StudentServiceImpl().getStudentById(Integer.parseInt(student));
                ClassServiceImpl service = new ClassServiceImpl();
                service.addStudentToClass(Integer.parseInt(classId),tempStudent);
            }
            doGet(request, response);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        new StudentServiceImpl().removeStudentFromClass(studentId);
        doGet(request, response);
    }
}
