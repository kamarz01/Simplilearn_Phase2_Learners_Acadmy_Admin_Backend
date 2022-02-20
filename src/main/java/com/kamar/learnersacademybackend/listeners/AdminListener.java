package com.kamar.learnersacademybackend.listeners;

import com.kamar.learnersacademybackend.entity.Admin;
import com.kamar.learnersacademybackend.service.Impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Objects;

@WebListener
public class AdminListener implements ServletContextListener{

    public AdminListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("App Started...");
        AdminServiceImpl service = new AdminServiceImpl();
        Admin checkAdmin  = service.getAdmin("admin","admin");
        if (Objects.isNull(checkAdmin)){
            service.createAdmin(new Admin("Kamar Zaghloul","admin","admin"));
        }
    }
}
