package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    //查询
    public List<Banner> findAll();

    //根据id 修改
    public void updateId(Banner banner);

    //查询总条数
    public Integer count();


    public void del(String[] id);

    public Map<String, Object> select(Integer page, Integer rows);

    //添加
    public void add(Banner banner);

    //根据id 修改路径
    public void updateIdPath(String id, String img_path);


}
