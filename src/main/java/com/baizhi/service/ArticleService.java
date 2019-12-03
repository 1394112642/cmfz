package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    public Map<String, Object> findAll(Integer page, Integer rows);

    public void update(Article article);

    public void add(Article article);

    public String pldelete(String[] id, String oper);

    public List<Article> queryByes(String val);
}
