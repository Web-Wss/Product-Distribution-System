package com.example.pds_api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pds_api.model.Address;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.model.OrderList;
import com.example.pds_api.model.Orders;
import com.example.pds_api.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService extends IService<User> {

//    获取收获地址
    List<Address> getAddressList(@RequestParam("userId")Integer userId);

//    新增地址信息
    Integer addAddressInfo(@RequestBody AddressDTO addressDTO);

//    修改地址信息
    Integer updateAddressInfo(@RequestBody AddressDTO addressDTO);

//    删除地址信息
    Integer deleteAddressInfo(@RequestParam("addressId")Integer addressId);

//    获取我的订单列表根据条件查询
    List<Orders> getMyOrderListByParam(@RequestParam("userId")Integer userId,
                                       @RequestParam("orderStatus")Integer orderStatus);

//    获取订单详情根据订单id
    List<OrderList> getOrderDetailByOrderId(@RequestParam("ordersId")Integer ordersId);

}
