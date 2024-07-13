package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("distributor")
public class Distributor {

    @TableId(type = IdType.AUTO)
    private Integer distributorId;//分销商id

    private String nickname;//昵称

    private String phone;//手机号

    private String password;//密码

    private Integer status;//状态

    private String createTime;//创建时间

    private Integer commissionRate;//分销商佣金比例

    private BigDecimal alreadyWithdrawn;//已提现佣金

    @TableField(exist = false)
    private Integer OrderCount;//订单数量
    @TableField(exist = false)
    private Integer totalOrderAmount;//订单总额
    @TableField(exist = false)
    private Integer commission;//佣金

}
