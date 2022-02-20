package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.service.ClassService;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ClassesServlet", value = "/classes")
public class ClassesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Class> classList = new ClassServiceImpl().getAllClasses();
        request.setAttribute("classList",classList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes.jsp");
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
            String name = request.getParameter("className");
            String year = request.getParameter("classYear");
            Class _class = new Class(name,Integer.parseInt(year));
            new ClassServiceImpl().createClass(_class);;
            doGet(request,response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        new ClassServiceImpl().removeClass(classId);
        doGet(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("className");
        String year = request.getParameter("classYear");
        String id = request.getParameter("classId");
        Class _class = new Class(name,Integer.parseInt(year));
        _class.setClassId(Integer.parseInt(id));
        new ClassServiceImpl().updateClass(_class);;
        doGet(request,response);
    }
}
