package com.baizhi.mapper;

import com.baizhi.entity.Ndate;

import java.util.List;

public interface UserMapper {
    public Integer count(String province);

    public List<Ndate> findDate();

}
