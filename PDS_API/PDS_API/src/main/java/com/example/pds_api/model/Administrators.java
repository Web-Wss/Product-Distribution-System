package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("administrators")
public class Administrators {
    @TableId
    private Integer administratorsId;//管理员id

    private String nickname;//管理员昵称

    private String phone;//管理员手机号

    private String password;//管理员密码

}
