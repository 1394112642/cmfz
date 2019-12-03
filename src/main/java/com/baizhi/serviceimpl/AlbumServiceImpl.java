package com.baizhi.serviceimpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public void add(Album album) {
        albumMapper.add(album);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> select(Integer page, Integer rows) {
        //总条数
        Integer count = albumMapper.count();
        //总页数
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        //数据库中的起始条
        Integer i = (page - 1) * rows;

        List<Album> select = albumMapper.select(i, rows);

        HashMap<String, Object> map = new HashMap<>();

        map.put("rows", select);//轮播图列表
        map.put("records", count);//总条数
        map.put("page", page);//当前页
        map.put("total", total);//总页数
        return map;
    }

    @Override
    public Integer selectCount(String id) {

        return albumMapper.selectCount(id);
    }

    @Override
    public void updateId(Album album) {
        albumMapper.updateId(album);
    }

    @Override
    public void del(String[] id) {
        albumMapper.del(id);

        chapterMapper.deleteAll(id);
    }

    @Override
    public void updateIdPath(String id, String cover) {
        albumMapper.updateIdPath(id, cover);
    }

    @Override
    public void updateIdCount(String id, Integer count) {
        albumMapper.updateIdCount(id, count);
    }


}
