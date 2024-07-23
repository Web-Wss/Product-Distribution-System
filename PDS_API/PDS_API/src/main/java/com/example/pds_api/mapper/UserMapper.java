package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Cart;
import com.example.pds_api.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


//    获取购物车已选中商品信息
    List<Cart> getCartSelectedGoods(Integer userId);

}
