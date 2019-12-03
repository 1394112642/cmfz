package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {

    //增加
    public void add(Album album);

    //分页查询
    public List<Album> select(@Param("start") Integer start, @Param("rows") Integer rows);

    //查询总条数
    public Integer count();

    //根据id查询章节数
    public Integer selectCount(String id);

    //根据id 修改
    public void updateId(Album album);

    public void del(String[] id);


    //根据id修改章节数
    public void updateIdCount(@Param("id") String id, @Param("count") Integer count);

    //根据id 修改路径
    public void updateIdPath(@Param("id") String id, @Param("cover") String cover);

}
