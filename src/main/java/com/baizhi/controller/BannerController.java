package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerController {

    @Autowired
    BannerService bannerService;


    @RequestMapping("findAll")
    @ResponseBody
    public Map<String, Object> findByPage(Integer page, Integer rows) {

        return bannerService.select(page, rows);

    }


    @RequestMapping("edit")
    @ResponseBody
    public String edit(String oper, Banner banner, String[] id) {
        if ("add".equals(oper)) {
            //添加
            String s = UUID.randomUUID().toString();
            banner.setId(s);
            banner.setCreate_date(new Date());
            bannerService.add(banner);
            return s;
        }

        if ("edit".equals(oper)) {
            //修改
            bannerService.updateId(banner);
        }
        if ("del".equals(oper)) {

            bannerService.del(id);

        }


        return null;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(MultipartFile img_path, String bannerId, HttpSession session) throws IOException {
        //获得图片存储的位置
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = img_path.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;


        img_path.transferTo(new File(realPath, newFileName));

        bannerService.updateIdPath(bannerId, newFileName);
    }

    @RequestMapping("download")
    @ResponseBody
    public void download(HttpServletResponse response) {
        List<Banner> all = bannerService.findAll();
        for (Banner banner : all) {
            banner.setImg_path("D:\\后期项目\\source\\cmfz\\src\\main\\webapp\\img\\" + banner.getImg_path());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图展示", "轮播图"),
                Banner.class, all);

        try {
            String encode = URLEncoder.encode("轮播图信息.xls", "UTF-8");
            response.setHeader("content-disposition", "attachment;fileName=" + encode);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
