package com.example.pds_api.service;

import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;

import java.util.List;

public interface CartService {

    //    添加商品到购物车
    Integer addGoodsToCart(AddGoodsToCartDTO addGoodsToCartDTO);

    //    获取购物车列表
    List<Cart> getCartListByUserId(Integer userId);

//    获取购物车总价根据用户id
    Integer getCartTotalPriceByUserId(Integer userId);

}
