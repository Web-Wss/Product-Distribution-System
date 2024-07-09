package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

@TableName("orders")
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer ordersId;//订单id

    private Integer userId;//用户id

    private Integer addressId;//地址id

    private BigDecimal orderTotalPrice;//订单总价

    private BigDecimal orderDiscountPrice;//订单优惠价格

    private BigDecimal orderActualPayment;//订单实际价格

    private String orderCreateTime;//订单创建时间

    private String orderRemarks;//订单备注

    private Integer orderStatus;//订单状态

    private Integer payType;//支付方式

    private String completionTime;//订单完成时间

    private Integer distributorId;//分销商id

}
