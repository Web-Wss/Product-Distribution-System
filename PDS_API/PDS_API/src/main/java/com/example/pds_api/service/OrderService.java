package com.example.pds_api.service;

import com.example.pds_api.model.OrderList;

import java.util.List;

public interface OrderService {

//    获取订单详情
    List<OrderList> getOrderListByOrderId(Integer ordersId);

}
