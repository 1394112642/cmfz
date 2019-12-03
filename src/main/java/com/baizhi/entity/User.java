package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;
    private String sex;
    private String headImg;
    private String nickname;
    private String name;
    private String status;
    private String province;
    private String city;
    private String sign;
    private Date CreateDate;
}
