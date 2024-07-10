package com.example.pds_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.AddressMapper;
import com.example.pds_api.mapper.OrdersMapper;
import com.example.pds_api.model.Address;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.model.OrderList;
import com.example.pds_api.model.Orders;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AddressMapper addressMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderService orderService;


//    获取地址列表
    @GetMapping("/getaddresslist")
    public Result getAddressList(@RequestParam("userId")Integer userId){
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Address> addresses = addressMapper.selectList(queryWrapper);
        return Result.success("获取地址列表成功",addresses);
    }

//    新增地址信息
    @PostMapping("/addaddressinfo")
    public Result addAddressInfo(@RequestBody AddressDTO addressDTO){
        Address address = new Address();
        address.setAddressInfo(addressDTO.getAddressInfo());
        address.setContacts(addressDTO.getContacts());
        address.setPhone(addressDTO.getPhone());
        address.setUserId(addressDTO.getUserId());
        address.setIsDefault(addressDTO.getIsDefault());
        System.out.println(address);
        int insert = addressMapper.insert(address);
        if (insert == 1){
            return Result.success("新增地址信息成功",insert);
        }else {
            return Result.fail("新增地址信息失败",insert);
        }
    }


//    修改地址信息
    @PostMapping("/updateaddressinfo")
    public Result updateAddressInfo(@RequestBody AddressDTO addressDTO){
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
        int update = addressMapper.update(address,new QueryWrapper<Address>().eq("address_id",addressDTO.getAddressId()));
        if (update == 1){
            return Result.success("修改地址信息成功",update);
        }else {
            return Result.fail("修改地址信息失败",update);
        }
    }

//    删除地址信息
    @PostMapping("/deleteaddressinfo")
    public Result deleteAddressInfo(@RequestParam("addressId")Integer addressId){
        int delete = addressMapper.delete(new QueryWrapper<Address>().eq("address_id",addressId));
        if (delete == 1){
            return Result.success("删除地址信息成功",delete);
        }else {
            return Result.fail("删除地址信息失败",delete);
        }
    }

//    获取我的订单列表根据条件查询
    @PostMapping("/getmyorderlistbyparam")
    public Result getMyOrderListByParam(@RequestParam("userId")Integer userId,
                                        @RequestParam("orderStatus")Integer orderStatus){
//        判断是否带参，带参根据带参查询，不带参查询全部
        if (orderStatus == 0){
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId)
                    .orderByDesc("order_create_time");
             List<Orders> orders = ordersMapper.selectList(queryWrapper);
            return Result.success("获取我的订单列表成功",orders);
        }else{
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId)
                    .eq("order_status",orderStatus)
                    .orderByDesc("order_create_time");
            List<Orders> orders = ordersMapper.selectList(queryWrapper);
            return Result.success("获取我的订单列表成功",orders);
        }
    }

//    获取订单详情根据订单id
    @PostMapping("/getorderdetailbyorderid")
    public Result getOrderDetailByOrderId(@RequestParam("ordersId")Integer ordersId){
        List<OrderList> orderListByOrderId = orderService.getOrderListByOrderId(ordersId);
        return Result.success("获取订单详情成功",orderListByOrderId);
    }




}
