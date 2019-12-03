package com.baizhi.serviceimpl;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterMapper chapterMapper;


    @Override
    public void add(Chapter chapter) {
        chapterMapper.add(chapter);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> select(Integer page, Integer rows, String albumid) {
        //总条数
        Integer count = chapterMapper.count(albumid);
        //总页数
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        //数据库中的起始条
        Integer i = (page - 1) * rows;
        List<Chapter> select = chapterMapper.select(i, rows, albumid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", select);//轮播图列表
        map.put("records", count);//总条数
        map.put("page", page);//当前页
        map.put("total", total);//总页数
        return map;
    }

    @Override
    public void updateIdPath(Chapter chapter) {
        chapterMapper.updateIdPath(chapter);
    }

    @Override
    public void updateId(Chapter chapter) {
        chapterMapper.updateId(chapter);
    }

    @Override
    public void del(String[] id) {
        chapterMapper.del(id);
    }
}
