package com.baizhi.serviceimpl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Transactional(propagation = Propagation.SUPPORTS)

    @Override
    public String selectUsername(String username, String password, String code, HttpSession session) {


        String validationCode = (String) session.getAttribute("validationCode");

        if (code.equals(validationCode)) {
            Admin admin1 = adminMapper.selectUsername(username);

            if (admin1 != null) {
                if (admin1.getPassword().equals(password)) {
                    session.setAttribute("name", username);
                    return "ok";
                }
                return "密码错误";
            }
            return "账号错误";
        }
        return "验证码错误";

    }
}
