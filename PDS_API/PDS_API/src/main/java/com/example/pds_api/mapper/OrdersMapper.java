package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.OrderList;
import com.example.pds_api.model.Orders;

public interface OrdersMapper extends BaseMapper<Orders> {

    OrderList getOrderListByOrderId(Integer ordersId);

}
