package com.kamar.learnersacademybackend.servlets;

import com.kamar.learnersacademybackend.entity.Admin;
import com.kamar.learnersacademybackend.service.Impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        if (Objects.isNull(userName) || userName.isEmpty()){
            response.sendRedirect("index.jsp");
        }else{
            response.sendRedirect("home.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = new AdminServiceImpl().getAdmin(userName,password);
        if (!Objects.isNull(admin)){
            HttpSession session = request.getSession();
            session.setAttribute("username",admin.getAdminUserName());
            response.sendRedirect("home.jsp");
        }else{
            request.setAttribute("error",true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request,response);
        }
    }
}
