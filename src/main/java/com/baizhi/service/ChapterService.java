package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {

    //增加
    public void add(Chapter chapter);

    //分页查询
    public Map<String, Object> select(Integer page, Integer rows, String albumid);

    //根据id 修改路径
    public void updateIdPath(Chapter chapter);

    //根据id 修改
    public void updateId(Chapter chapter);

    public void del(String[] id);
}
