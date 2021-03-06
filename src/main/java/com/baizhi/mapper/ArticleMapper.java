package com.baizhi.mapper;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    public List<Article> findAll(@Param("start") Integer start, @Param("rows") Integer rows);

    public Integer count();

    public void update(Article article);

    public void add(Article article);

    public void pldelete(String[] id);

    public List<Article> select();


}
