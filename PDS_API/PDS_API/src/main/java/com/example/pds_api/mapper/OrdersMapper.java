package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.OrderList;
import com.example.pds_api.model.Orders;

import java.util.List;

public interface OrdersMapper extends BaseMapper<Orders> {

    List<OrderList> getOrderListByOrderId(Integer ordersId);

}
