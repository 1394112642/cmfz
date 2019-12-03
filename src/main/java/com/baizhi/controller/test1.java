/*
package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RespectBinding;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@RespectBinding
@RequestMapping("kindeditor1")
public class test1 {

    @RequestMapping("kindeditor1")
    public EchartsMap<String,Object> upload(MultipartFile img, HttpServletRequest request) throws IOException {
        EchartsMap<String, Object> map = new HashMap<>();
        String realPath = request.getServletContext().getRealPath("/music");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String newFileName =new Date().getTime()+"_"+img.getOriginalFilename();
        img.transferTo(new File(realPath,newFileName));
        //获取当前网站的协议名
        String scheme = request.getScheme();
        //获取当前本机的ip地址
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        //获取当前端口号
        int port = request.getServerPort();
        //获取项目名
        String contextPath = request.getContextPath();

        String url =scheme+"://"+hostAddress+":"+port+contextPath+"/music/"+newFileName;

        map.put("error",0);
        map.put("url",url);
        return map;
    }
    @RequestMapping("all")
    public EchartsMap<String,Object> all(HttpServletRequest request){
        //创建一个最大的map
        HashMap<String, Object> map1 = new HashMap<>();
        //创建list集合存放小map
        List<EchartsMap<String,Object>> list = new ArrayList<>();
        String realPath = request.getServletContext().getRealPath("/music");
        File file = new File(realPath);
        //获取文件夹中的所有文件
        String[] list1 = file.list();
        for (String s : list1) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("is_dir",false);
            map.put("has_file",false);
            //获取文件的大小
            File file1 = new File(realPath, s);
            long length = file1.length();
            map.put("filesize",length);
            map.put("dir_path","");
            map.put("is_photo",true);
            //获取文件的后缀名
            String s1 = s.substring(s.lastIndexOf(".") + 1);
            map.put("filetype",s1);
            map.put("filename",s);
            if(s.contains("_")){
                String s2 =s.split("_")[0];
                Long aLong = Long.valueOf(s2);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format1 = format.format(aLong);
                map.put("datetime",format1);

            }else {
                map.put("datetime",new Date());
            }
            list.add(map);
        }


    return map1;
    }
}
*/
