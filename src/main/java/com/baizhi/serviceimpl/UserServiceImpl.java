package com.baizhi.serviceimpl;

import com.baizhi.entity.Ndate;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count(String province) {
        return userMapper.count(province);
    }

    @Override
    public List<Ndate> findDate() {
        return userMapper.findDate();
    }
}
