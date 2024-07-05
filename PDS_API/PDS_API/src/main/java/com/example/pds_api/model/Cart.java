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
@TableName("cart")
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer cartId;//购物车id

    private Integer userId;//用户id

    private Integer goodsId;//商品id

    private Integer goodsNumber;//商品数量

    private Integer goodsSelectedStatus;//商品选中状态

    private Goods goods;

}
