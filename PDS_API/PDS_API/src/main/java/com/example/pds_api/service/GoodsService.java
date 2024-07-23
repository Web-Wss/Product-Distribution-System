package com.example.pds_api.service;

import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.Reduction;

import java.util.List;

public interface GoodsService {
    Integer addGoodsToCart(AddGoodsToCartDTO addGoodsToCartDTO);

    List<Cart> getCartListByUserId(Integer userId);

    Integer getCartTotalPriceByUserId(Integer userId);

    Integer updateCartInfo(CartDTO cartDTO);

    Integer deleteCartByCartId(Integer cartId);

    List<Reduction> getReductionList();

    Object getDefaultAddress(Integer userId);
}
