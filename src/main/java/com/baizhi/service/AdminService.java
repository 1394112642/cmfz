package com.baizhi.service;

import javax.servlet.http.HttpSession;

public interface AdminService {


    public String selectUsername(String username, String password, String code, HttpSession session);
}
