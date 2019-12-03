package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {

    //增加
    public void add(Album album);

    //分页查询
    public Map<String, Object> select(Integer page, Integer rows);

    //根据id查询章节数
    public Integer selectCount(String id);

    //根据id 修改
    public void updateId(Album album);

    public void del(String[] id);


    //根据id 修改路径
    public void updateIdPath(String id, String cover);

    //根据id修改章节数
    public void updateIdCount(String id, Integer count);
}
