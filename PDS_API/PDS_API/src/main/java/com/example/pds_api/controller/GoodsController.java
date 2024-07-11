package com.example.pds_api.controller;

import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Resource
    private GoodsService goodsService;

//    添加商品到购物车
    @PostMapping("/addgoodstocart")
    public Result addGoodsToCart(@RequestBody AddGoodsToCartDTO addGoodsToCartDTO) {
        Integer integer = goodsService.addGoodsToCart(addGoodsToCartDTO);
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
        List<Cart> cartListByUserId = goodsService.getCartListByUserId(userId);
        return Result.success(cartListByUserId);
    }
//    获取购物车总价根据用户id
    @PostMapping("/getcarttotalpricebyuserid")
    public Result getCartTotalPriceByUserId(@RequestParam("userId") Integer userId) {
        Integer cartTotalPriceByUserId = goodsService.getCartTotalPriceByUserId(userId);
        return Result.success(cartTotalPriceByUserId);
    }

//    修改购物车信息
    @PostMapping("/updatecartinfo")
    public Result updateCartInfo(@RequestBody CartDTO cartDTO) {
        Integer integer = goodsService.updateCartInfo(cartDTO);
        if (integer == 1) {
            return Result.success("修改成功");
        }
        return Result.fail("修改失败");
    }

//    删除购物车根据购物车id
    @PostMapping("/deletecartbycartid")
    public Result deleteCartByCartId(@RequestParam("cartId") Integer cartId) {
        Integer integer = goodsService.deleteCartByCartId(cartId);
        if (integer == 1) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }


//    获取生成订单信息根据用户id
    @PostMapping("/getcartlistbyuseridandselectedstatus")
    public Result getCartListByUserIdAndSelectedStatus(@RequestParam("userId") Integer userId) {
        HashMap<String, Object> cartListByUserIdAndSelectedStatus = goodsService.getCartListByUserIdAndSelectedStatus(userId);
        return Result.success("生成订单信息",cartListByUserIdAndSelectedStatus);
    }

//    获取满减规则列表
    @GetMapping("/getreductionlist")
    public Result getReductionList() {
        List<Reduction> reductionList = goodsService.getReductionList();
        return Result.success("获取满减规则列表",reductionList);
    }


//    生成订单
    @PostMapping("/generateorder")
    public Result generateOrder(@RequestBody GenerateOrderDTO generateOrderDTO) {
        Integer integer = goodsService.generateOrder(generateOrderDTO);
        if (integer == 1) {
            return Result.success("生成订单成功");
        }
        return Result.fail("生成订单失败");
    }





}
