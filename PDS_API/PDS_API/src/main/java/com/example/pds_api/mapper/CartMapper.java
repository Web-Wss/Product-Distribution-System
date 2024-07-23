package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Cart;

import java.util.List;


public interface CartMapper extends BaseMapper<Cart> {

    //    获取购物车列表根据用户id
    List<Cart> getCartListByUserId(Integer userId);

    //    获取购物车总价根据用户id
    Integer getCartTotalPriceByUserId(Integer userId);

}
