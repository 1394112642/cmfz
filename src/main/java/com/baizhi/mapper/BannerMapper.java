package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    //查询
    public List<Banner> findAll();

    //分页查询
    public List<Banner> select(@Param("start") Integer start, @Param("rows") Integer rows);

    //查询总条数
    public Integer count();

    //根据id 修改
    public void updateId(Banner banner);

    public void del(String[] id);

    //添加
    public void add(Banner banner);


    //根据id 修改路径
    public void updateIdPath(@Param("id") String id, @Param("img_path") String img_path);


}
