package com.example.pds_api.service;

import com.example.pds_api.model.DTO.AddGoodsToCartDTO;

public interface CartService {

    //    添加商品到购物车
    Integer addGoodsToCart(AddGoodsToCartDTO addGoodsToCartDTO);

}
