package com.baizhi.service;


import com.baizhi.entity.Ndate;

import java.util.List;

public interface UserService {
    public Integer count(String province);

    public List<Ndate> findDate();
}
