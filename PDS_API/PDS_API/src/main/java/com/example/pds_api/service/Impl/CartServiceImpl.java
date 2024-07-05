package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.CartMapper;
import com.example.pds_api.model.Cart;
import com.example.pds_api.model.DTO.AddGoodsToCartDTO;
import com.example.pds_api.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public Integer addGoodsToCart(AddGoodsToCartDTO addGoodsToCartDTO) {
        Cart cart = new Cart();
        cart.setGoodsId(addGoodsToCartDTO.getGoodsId());
        cart.setGoodsNumber(addGoodsToCartDTO.getGoodsNumber());
        cart.setUserId(addGoodsToCartDTO.getUserId());
        cart.setGoodsSelectedStatus(1);
        Integer integer = 0;
//        根据用户id和商品id查询购物车表中是否已经有该商品，如果有，则更新数量，如果没有，则添加
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", addGoodsToCartDTO.getUserId())
                .eq("goods_id", addGoodsToCartDTO.getGoodsId());
        Cart cart1 = cartMapper.selectOne(wrapper);
        System.out.println("cart1"+cart1);
        if (cart1 != null) {
            System.out.println("进入修改操作");
            cart1.setGoodsNumber(cart1.getGoodsNumber() + cart.getGoodsNumber());
            //        判断cart1中商品数量是否大于剩余库存，如果大于，将cart1数量设置为最大库存，如果小于库存，则不做改变
            System.out.println("数量"+cart1);
            if (cart1.getGoodsNumber() > addGoodsToCartDTO.getRemainingInventory()) {
                cart1.setGoodsNumber(addGoodsToCartDTO.getRemainingInventory());
            }else{
                cart1.setGoodsNumber(cart1.getGoodsNumber());
            }
            //        修改购物车表中商品数量
            System.out.println("修改的数据"+cart1);
            integer = cartMapper.update(cart1,wrapper);
            System.out.println("修改后的数据"+integer);
        }

//        若是没有，则直接加购
        if (cart1 == null){
            integer = cartMapper.insert(cart);
        }
        return integer;
    }
}
