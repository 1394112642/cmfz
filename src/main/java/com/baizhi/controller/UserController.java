package com.baizhi.controller;

import com.baizhi.entity.EchartsMap;
import com.baizhi.entity.Ndate;
import com.baizhi.entity.Province;
import com.baizhi.service.ProvinceService;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ProvinceService provinceService;

    @RequestMapping("findAll")
    public List<EchartsMap> findall() {
        ArrayList<EchartsMap> maps = new ArrayList<>();
        List<Province> province = provinceService.findProvince();
        for (Province province1 : province) {
            Integer count = userService.count(province1.getName());
            EchartsMap map = new EchartsMap(province1.getName(), count);
            maps.add(map);
        }
        return maps;
    }

    @RequestMapping("date")
    public Map<String, Object> date() {
        Map<String, Object> map = new HashMap<>();
        List<Ndate> date = userService.findDate();
        List<Integer> integers = new ArrayList<>();
        ArrayList<Date> dates = new ArrayList<>();
        for (Ndate ndate : date) {
            integers.add(ndate.getCount());
            dates.add(ndate.getCreate_date());
        }
        map.put("x", integers);
        map.put("y", dates);
        return map;
    }

    @RequestMapping("qq")
    public void aa(String aa, HttpSession session) {
        String name = (String) session.getAttribute("name");
        if (name == null) {
            name = "游客";
        }
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-033e2140859e4eb4a5882dcf4cfa0898");
        goEasy.publish("qq", name + ":" + aa);
    }

}
