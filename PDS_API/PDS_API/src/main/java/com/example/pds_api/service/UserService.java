package com.example.pds_api.service;

import com.example.pds_api.model.Address;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.model.OrderList;

import javax.naming.InsufficientResourcesException;
import java.util.List;

public interface UserService {
    List<Address> getAddressList(Integer userId);

    Integer addAddressInfo(AddressDTO addressDTO);

    Integer updateAddressInfo(AddressDTO addressDTO);

    Integer deleteAddressInfo(Integer addressId);

    Object getMyOrderListByParam(Integer userId, Integer orderStatus);

    List<OrderList> getOrderDetailByOrderId(Integer ordersId);

    Integer generateOrder(GenerateOrderDTO generateOrderDTO) throws InsufficientResourcesException;

}
