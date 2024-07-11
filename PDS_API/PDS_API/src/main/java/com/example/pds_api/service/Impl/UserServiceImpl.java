package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pds_api.mapper.AddressMapper;
import com.example.pds_api.mapper.OrdersMapper;
import com.example.pds_api.mapper.UserMapper;
import com.example.pds_api.model.*;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private AddressMapper addressMapper;
    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public List<Address> getAddressList(Integer userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return addressMapper.selectList(queryWrapper);
    }

    @Override
    public Integer addAddressInfo(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddressInfo(addressDTO.getAddressInfo());
        address.setContacts(addressDTO.getContacts());
        address.setPhone(addressDTO.getPhone());
        address.setUserId(addressDTO.getUserId());
        address.setIsDefault(addressDTO.getIsDefault());
        int insert = addressMapper.insert(address);
        if (insert == 1) {
            return insert;
        }else {
            return insert;
        }
    }

    @Override
    public Integer updateAddressInfo(AddressDTO addressDTO) {
        Address address = new Address();
        Address address_default = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setUserId(addressDTO.getUserId());
        address.setAddressInfo(addressDTO.getAddressInfo());
        address.setContacts(addressDTO.getContacts());
        address.setPhone(addressDTO.getPhone());
        address.setIsDefault(addressDTO.getIsDefault());
        //        判断是否只改默认地址
        if (addressDTO.getIsDefault() == 1){
            //        将该用户地址全部改为0
            address_default.setIsDefault(0);
            addressMapper.update(address_default,new QueryWrapper<Address>().eq("user_id",addressDTO.getUserId()));
        }
//        修改地址信息
        return addressMapper.update(address,new QueryWrapper<Address>().eq("address_id",addressDTO.getAddressId()));

    }

    @Override
    public Integer deleteAddressInfo(Integer addressId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("address_id",addressId);
        return  addressMapper.delete(queryWrapper);

    }

    @Override
    public List<Orders> getMyOrderListByParam(Integer userId, Integer orderStatus) {
        //        判断是否带参，带参根据带参查询，不带参查询全部
        if (orderStatus == 0){
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId)
                    .orderByDesc("order_create_time");
            return ordersMapper.selectList(queryWrapper);
        }else{
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId)
                    .eq("order_status",orderStatus)
                    .orderByDesc("order_create_time");
            return ordersMapper.selectList(queryWrapper);
        }
    }

    @Override
    public List<OrderList> getOrderDetailByOrderId(Integer ordersId) {
        return  ordersMapper.getOrderListByOrderId(ordersId);
    }
}
