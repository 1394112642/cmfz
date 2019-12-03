package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.ArticleRepsitory.ArticleRepsitory;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.mapper.BannerMapper;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BannerMapper bannerMapper;

    @Test
    public void contextLoads() {
        List<Banner> all = bannerMapper.findAll();
        for (Banner banner : all) {
            banner.setImg_path("D:\\后期项目\\source\\cmfz\\src\\main\\webapp\\img\\" + banner.getImg_path());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图展示", "轮播图"),
                Banner.class, all);
        try {
            workbook.write(new FileOutputStream(new File("E:/a.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void qq() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-033e2140859e4eb4a5882dcf4cfa0898");
        goEasy.publish("qq", "Hello, GoEasy !");
    }

    @Autowired
    ArticleRepsitory articleRepsitory;
    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void add() {
        List<Article> select = articleMapper.select();
        for (Article article : select) {
            articleRepsitory.save(article);
        }
    }
}
