package com.kamar.learnersacademybackend.service;

import com.kamar.learnersacademybackend.entity.Admin;
import com.kamar.learnersacademybackend.entity.Class;

public interface AdminService {
    public Admin getAdmin(String username,String password);
    public void createAdmin(Admin admin);

}
