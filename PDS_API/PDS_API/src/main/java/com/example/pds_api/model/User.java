package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String phone;

    private Integer userStatus;

    private String createTime;

}
