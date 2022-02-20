package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Class;
import com.kamar.learnersacademybackend.entity.Subject;
import com.kamar.learnersacademybackend.service.Impl.ClassServiceImpl;
import com.kamar.learnersacademybackend.service.Impl.SubjectServiceImpl;
import org.hibernate.Hibernate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SubjectClassServlet", value = "/subjectClass")
public class SubjectClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Class> classList = new ClassServiceImpl().getAllClassesWithSubjects();
        List<Subject> subjectList = new SubjectServiceImpl().getAllSubject();
        request.setAttribute("classList",classList.stream().distinct().collect(Collectors.toList()));
        request.setAttribute("subjectList",subjectList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("subjectClass.jsp");
        dispatcher.include(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("classId");
        String[] subjects = request.getParameterValues("subjects");
        if (subjects != null && subjects.length > 0){
            List<Subject> subList = new ArrayList<>();
            for (String s:subjects) {
                Subject tempSubject = new SubjectServiceImpl().getSubjectById(Integer.parseInt(s));
                subList.add(tempSubject);
            }
            ClassServiceImpl service = new ClassServiceImpl();
            Class classToUpdate = service.getClassById(Integer.parseInt(classId));
            classToUpdate.setSubjects(subList);
            service.updateClass(classToUpdate);
            doGet(request,response);
        }
    }
}
