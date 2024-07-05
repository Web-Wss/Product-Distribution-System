package com.example.pds_api.controller;

import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
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

}
