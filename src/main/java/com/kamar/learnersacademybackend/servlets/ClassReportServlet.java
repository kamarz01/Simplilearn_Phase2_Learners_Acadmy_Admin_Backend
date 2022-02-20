package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Student;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "ClassReportServlet", value = "/classReport")
public class ClassReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("classList", new ClassServiceImpl().getAllClassesWithAllData());
        RequestDispatcher dispatcher = request.getRequestDispatcher("classReport.jsp");
        dispatcher.include(request, response);
    }
}
