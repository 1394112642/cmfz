package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chapter {
    private String id;
    private String title;
    private String size;
    private String long_time;
    private Date upload_date;
    private String filepath;
    private String album_id;


}
