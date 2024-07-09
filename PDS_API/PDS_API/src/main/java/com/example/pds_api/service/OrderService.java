package com.example.pds_api.service;

import com.example.pds_api.model.OrderList;

public interface OrderService {

//    获取订单详情
    OrderList getOrderListByOrderId(Integer ordersId);

}
