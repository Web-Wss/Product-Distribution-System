package com.example.pds_api.service.Impl;

import com.example.pds_api.mapper.OrdersMapper;
import com.example.pds_api.model.OrderList;
import com.example.pds_api.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public OrderList getOrderListByOrderId(Integer ordersId) {
        return ordersMapper.getOrderListByOrderId(ordersId);
    }
}
