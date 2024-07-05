package com.example.pds_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.CartMapper;
import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private CartService cartService;

//    添加商品到购物车
    @PostMapping("/addgoodstocart")
    public Result addGoodsToCart(@RequestBody AddGoodsToCartDTO addGoodsToCartDTO) {
        Integer integer = cartService.addGoodsToCart(addGoodsToCartDTO);
        if (integer == 1) {
            return Result.success("添加成功");
        }
        if (integer == 0) {
            return Result.fail("添加失败");
        }
        return Result.fail("添加失败");
    }

//    获取购物车列表根据用户id
    @PostMapping("/getcartlistbyuserid")
    public Result getCartListByUserId(@RequestParam("userId") Integer userId) {
        List<Cart> carts = cartService.getCartListByUserId(userId);
        return Result.success(carts);
    }
//    获取购物车总价根据用户id
    @PostMapping("/getcarttotalpricebyuserid")
    public Result getCartTotalPriceByUserId(@RequestParam("userId") Integer userId) {
        Integer cartTotalPriceByUserId = cartService.getCartTotalPriceByUserId(userId);
        return Result.success(cartTotalPriceByUserId);
    }













}
