package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;


    @RequestMapping("findAll")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        return albumService.select(page, rows);
    }


    @RequestMapping("edit")
    public String edit(String oper, Album album, String[] id) {
        if ("add".equals(oper)) {
            //添加
            String s = UUID.randomUUID().toString();
            album.setId(s);//id
            album.setCount(0);//设置初始章节数
            album.setPublish_date(new Date());//上传时间
            System.out.println(album);
            albumService.add(album);
            return s;
        }
        if ("edit".equals(oper)) {
            //修改
            albumService.updateId(album);
        }
        if ("del".equals(oper)) {

            albumService.del(id);

        }

        return null;
    }


    @RequestMapping("/upload")
    public void upload(MultipartFile cover, String bannerId, HttpSession session) throws IOException {
        //获得图片存储的位置
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = cover.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;


        cover.transferTo(new File(realPath, newFileName));
        albumService.updateIdPath(bannerId, newFileName);
    }


}
