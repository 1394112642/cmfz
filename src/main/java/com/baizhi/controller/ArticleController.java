package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> all = articleService.findAll(page, rows);
        return all;
    }

    @RequestMapping("update")
    public void update(Article article) {
        articleService.update(article);
    }

    @RequestMapping("add")
    public void add(Article article) {
        articleService.add(article);
    }

    @RequestMapping("edit")
    public String add(String oper, String[] id) {
        articleService.pldelete(id, oper);
        return null;
    }

    @RequestMapping("es")
    public List<Article> queryByes(String esvalue) {
        List<Article> articles = articleService.queryByes(esvalue);
        return articles;
    }
}
