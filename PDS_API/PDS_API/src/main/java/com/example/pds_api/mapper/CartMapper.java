package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Cart;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {

    List<Cart> getCartListByUserId(Integer userId);

    Integer getCartTotalPriceByUserId(Integer userId);

}
