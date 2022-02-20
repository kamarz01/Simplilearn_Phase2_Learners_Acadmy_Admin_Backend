package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Subject;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.SubjectServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SubjectServlet", value = "/subject")
public class SubjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Subject> subjectList = new SubjectServiceImpl().getAllSubject();
        request.setAttribute("subjectList",subjectList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subjects.jsp");
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
            String name = request.getParameter("subjectName");
            String code = request.getParameter("subjectCode");
            String desc = request.getParameter("subjectDescription");
            Subject subject = new Subject(name,code,desc);
            new SubjectServiceImpl().createSubject(subject);;
            doGet(request,response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        new SubjectServiceImpl().removeSubject(subjectId);
        doGet(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("subjectName");
        String code = request.getParameter("subjectCode");
        String desc = request.getParameter("subjectDescription");
        String id = request.getParameter("subjectId");
        Subject subject = new Subject(name,code,desc);
        subject.setSubjectId(Integer.parseInt(id));
        new SubjectServiceImpl().updateSubject(subject);;
        doGet(request,response);
    }
}
