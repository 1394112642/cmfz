package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Excel(name = "主键")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "描述")
    private String des;
    @Excel(name = "日期", format = "yyyy年MM月dd日")
    private Date create_date;
    @Excel(name = "图片", type = 2, width = 30, height = 20)
    private String img_path;
}
