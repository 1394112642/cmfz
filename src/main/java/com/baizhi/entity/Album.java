package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    private String id;
    private String title;
    private Integer score;
    private String author;
    private String beam;//波音
    private Integer count;//条数
    private String content;
    private String status;
    private Date publish_date;
    private String cover;

}
