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
@TableName("address")
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer addressId;//地址id

    private Integer userId;//用户id

    private String addressInfo;//地址信息

    private String contacts;//联系人

    private String phone;//手机号

    private Integer isDefault;//是否默认

}
