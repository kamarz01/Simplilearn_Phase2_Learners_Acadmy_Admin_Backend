package com.kamar.learnersacademybackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    @OrderColumn
    private int adminId;

    @Column(name = "adminName")
    private String adminName;

    @Column(name = "adminUserName")
    private String adminUserName;

    @Column(name = "adminPassword")
    private String adminPassword;

    public Admin() {
    }

    public Admin(String adminName, String adminUserName, String adminPassword) {
        this.adminName = adminName;
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
