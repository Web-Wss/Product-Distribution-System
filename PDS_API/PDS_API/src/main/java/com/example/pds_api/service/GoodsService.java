package com.example.pds_api.service;

import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.model.Reduction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface GoodsService {

//    添加商品到购物车
    Integer addGoodsToCart(@RequestBody AddGoodsToCartDTO addGoodsToCartDTO);

//    获取购物车列表根据用户id
    List<Cart> getCartListByUserId(@RequestParam("userId") Integer userId);

//    获取购物车总价根据用户id
    Integer getCartTotalPriceByUserId(@RequestParam("userId") Integer userId);

//    修改购物车信息
    Integer  updateCartInfo(@RequestBody CartDTO cartDTO);

//    删除购物车根据购物车id
    Integer deleteCartByCartId(@RequestParam("cartId") Integer cartId);

//    获取生成订单信息根据用户id
    HashMap<String,Object> getCartListByUserIdAndSelectedStatus(@RequestParam("userId") Integer userId);

//    获取满减规则列表
    List<Reduction> getReductionList();

//    生成订单
    Integer generateOrder(@RequestBody GenerateOrderDTO generateOrderDTO);

}
