package com.baizhi.serviceimpl;

import com.baizhi.entity.Province;
import com.baizhi.mapper.ProvinceMapper;
import com.baizhi.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    ProvinceMapper provinceMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Province> findProvince() {
        return provinceMapper.findProvince();
    }
}
