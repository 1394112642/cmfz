package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("kindeditor")
public class Kindeditor {
    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile img, HttpServletRequest request) throws IOException {
        //返回值是一个map
        Map<String, Object> map = new HashMap<>();
        //获取文件的上传路径
        String realPath = request.getServletContext().getRealPath("/img");
        //将string 转为 File
        File file = new File(realPath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //不存在 则创建
            file.mkdirs();
        }
        //设置文件新名字 避免图片重复
        String newFileName = new Date().getTime() + "_" + img.getOriginalFilename();
        //进行拷贝
        img.transferTo(new File(realPath, newFileName));

        //获取当前网站的协议名  http
        String scheme = request.getScheme();
        //获取当前本机ip地址
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        //获取当前端口号
        int port = request.getServerPort();
        //获取项目名
        String path = request.getContextPath();
        //动态拼接 对应规定的格式
        String url = scheme + "://" + hostAddress + ":" + port + path + "/img/" + newFileName;

        //"http://localhost:8989/cmfz/img/"
        map.put("error", 0);
        map.put("url", url);
        return map;
    }

    @RequestMapping("allImages")
    public Map<String, Object> all(HttpSession session, HttpServletRequest request) throws UnknownHostException {
        Map<String, Object> map1 = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        //获取文件夹中的所有内容 名字.后缀
        String[] allimg = file.list();
        for (String s : allimg) {
            Map<String, Object> map = new HashMap<>();
            map.put("is_dir", false);
            map.put("has_file", false);
            //获取文件的大小
            File file1 = new File(realPath, s);
            long length = file1.length();
            map.put("filesize", length);
            map.put("dir_path", "");
            map.put("is_photo", true);
            //获取文件的后缀名
            String s1 = s.substring(s.lastIndexOf(".") + 1);
            map.put("filetype", s1);
            map.put("filename", s);
            if (s.contains("_")) {
                String s2 = s.split("_")[0];
                Long aLong = Long.valueOf(s2);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format1 = format.format(aLong);
                map.put("datetime", format1);
            } else {
                map.put("datetime", new Date());
            }

            list.add(map);
        }

        map1.put("moveup_dir_path", "");
        map1.put("current_dir_path", "");
        //获取当前网站的协议名  http
        String scheme = request.getScheme();
        //获取当前本机ip地址
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        //获取当前端口号
        int port = request.getServerPort();
        //获取项目名
        String path = request.getContextPath();
        String url = scheme + "://" + hostAddress + ":" + port + path + "/img/";
        map1.put("current_url", url);
        int size = list.size();
        map1.put("total_count", size);
        map1.put("file_list", list);

        /*
        * {
            "moveup_dir_path": "",
            "current_dir_path": "",
            "current_url": "\/ke4\/php\/..\/attached\/",
            "total_count": 1,
            "file_list": [{
                "is_dir": false,
                "has_file": false,
                "filesize": 208736,
                "dir_path": "",
                "is_photo": true,
                "filetype": "jpg",
                "filename": "1241601537255682809.jpg",
                "datetime": "2018-06-06 00:36:39"
            },]
        }
        * */

        return map1;
    }
}
