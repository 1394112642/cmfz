package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;
    @Autowired
    AlbumService albumService;


    @RequestMapping("findAll")
    public Map<String, Object> findByPage(Integer page, Integer rows, String albumid) {

        return chapterService.select(page, rows, albumid);

    }


    @RequestMapping("edit")
    public String edit(String oper, Chapter chapter, String[] id, HttpSession session, String albumid) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        if ("add".equals(oper)) {


            String ss = UUID.randomUUID().toString();

            chapter.setId(ss);//id
            chapter.setUpload_date(new Date());
            chapter.setAlbum_id(albumid);
            chapterService.add(chapter);
            Integer integer = albumService.selectCount(albumid);

            albumService.updateIdCount(albumid, integer + 1);
            return ss;
        }
        if ("edit".equals(oper)) {
            //修改
            chapterService.updateId(chapter);
        }
        if ("del".equals(oper)) {
            Integer integer = albumService.selectCount(albumid);
            albumService.updateIdCount(albumid, integer - id.length);
            chapterService.del(id);

        }

        return null;
    }


    @RequestMapping("/upload")
    public void upload(MultipartFile filepath, String bannerId, HttpSession session) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        //获得音频存储的位置
        String realPath = session.getServletContext().getRealPath("/music");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = filepath.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        filepath.transferTo(new File(realPath, newFileName));


        //获取文件位置
        String realPath1 = session.getServletContext().getRealPath("/music/" + newFileName);
        System.out.println(realPath1);
        File file1 = new File(realPath1);

        //获取文件大小 单位是字节 byte
        long length = file1.length();
        //获取音频时长 单位是秒  AudioFile类需要引入额外依赖 jaudiotagger
        AudioFile read = AudioFileIO.read(file1);
        AudioHeader audioHeader = read.getAudioHeader();
        int trackLength = audioHeader.getTrackLength();
        //获取分钟数
        Integer m = trackLength / 60;
        //获取秒数
        Integer s = trackLength % 60;
        String long_time = m + "分" + s + "秒";//歌曲时长

        //将文件大小强转成double类型
        double size = (double) length;
        //获取文件大小   单位是MB
        double ll = size / 1024 / 1024;
        //取double小数点后两位  四舍五入
        BigDecimal bg = new BigDecimal(ll).setScale(2, RoundingMode.UP);
        String size1 = bg + "MB";//歌曲大小
        Chapter chapter = new Chapter();
        chapter.setId(bannerId);
        chapter.setFilepath(newFileName);
        chapter.setLong_time(long_time);
        chapter.setSize(size1);
        chapterService.updateIdPath(chapter);
    }


    @RequestMapping("/download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String realPath = request.getSession().getServletContext().getRealPath("/music");
        FileInputStream is = new FileInputStream(new File(realPath, fileName));
        ServletOutputStream os = response.getOutputStream();

        String s = fileName.split("_")[1];

        String s1 = URLEncoder.encode(s, "utf-8");

        String replace = s1.replace("+", "%20");

        response.setHeader("content-disposition", "attachment;fileName=" + replace);
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }


}
