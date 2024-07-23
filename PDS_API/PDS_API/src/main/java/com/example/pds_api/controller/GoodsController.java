package com.example.pds_api.controller;


import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.DTO.CartDTO;
import com.example.pds_api.model.Reduction;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    //    获取满减规则列表
    @GetMapping("/getreductionlist")
    public Result getReductionList() {
        List<Reduction> reductionList = goodsService.getReductionList();
        return Result.success("获取满减规则列表",reductionList);
    }


//    获取默认收获地址
    @GetMapping("/getdefaultaddress")
    public Result getDefaultAddress(@RequestParam("userId") Integer userId) {
        return Result.success("获取默认收获地址",goodsService.getDefaultAddress(userId));
    }



}
