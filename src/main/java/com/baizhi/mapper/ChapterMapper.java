package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {

    //增加
    public void add(Chapter chapter);

    //查询总条数
    public Integer count(String albumid);

    //分页查询
    public List<Chapter> select(@Param("start") Integer start, @Param("rows") Integer rows, @Param("albumid") String albumid);

    //根据id 修改路径
    public void updateIdPath(Chapter chapter);

    public void deleteAll(String[] album_id);


    //根据id 修改
    public void updateId(Chapter chapter);

    public void del(String[] id);
}
