package com.baizhi.serviceimpl;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findAll() {
        return bannerMapper.findAll();
    }

    @Override
    public void updateId(Banner banner) {
        bannerMapper.updateId(banner);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count() {
        return bannerMapper.count();

    }

    public void del(String[] id) {

        bannerMapper.del(id);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> select(Integer page, Integer rows) {
        //总条数
        Integer count = bannerMapper.count();
        //总页数
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        //数据库中的起始条
        Integer i = (page - 1) * rows;

        List<Banner> select = bannerMapper.select(i, rows);

        HashMap<String, Object> map = new HashMap<>();

        map.put("rows", select);//轮播图列表
        map.put("records", count);//总条数
        map.put("page", page);//当前页
        map.put("total", total);//总页数
        return map;

    }

    @Override
    public void add(Banner banner) {
        bannerMapper.add(banner);
    }

    @Override
    public void updateIdPath(String id, String img_path) {
        bannerMapper.updateIdPath(id, img_path);
    }
}
