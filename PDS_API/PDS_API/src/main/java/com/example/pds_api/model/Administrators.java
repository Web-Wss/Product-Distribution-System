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
    private Integer administratorsId;

    private String nickname;

    private String phone;

    private String password;

}
