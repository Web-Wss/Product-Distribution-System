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
@TableName("order_list")
public class OrderList {

    @TableId(value = "order_list_id",type = IdType.AUTO)
    private Integer orderListId;//订单列表id

    private Integer ordersId;//订单id

    private Integer goodsId;//商品id

    private BigDecimal goodsPrice;//商品单价

    private Integer goodsNumber;//商品数量

    private BigDecimal priceSubtotal;//商品小计
//    收货地址信息
    private Address address;
//    订单表信息
    private Orders orders;
//    商品表
    private Goods goods;

}
