package com.example.pds_api.controller;

import com.example.pds_api.model.Address;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.model.DTO.GenerateOrderDTO;
import com.example.pds_api.model.OrderList;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.naming.InsufficientResourcesException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    //    获取地址列表
    @GetMapping("/getaddresslist")
    public Result getAddressList(@RequestParam("userId")Integer userId){
        List<Address> addressList = userService.getAddressList(userId);
        return Result.success("获取地址列表成功",addressList);
    }


    //    新增地址信息
    @PostMapping("/addaddressinfo")
    public Result addAddressInfo(@RequestBody AddressDTO addressDTO){
        Integer integer = userService.addAddressInfo(addressDTO);
        if (integer == 1){
            return Result.success("新增地址信息成功",integer);
        }else {
            return Result.fail("新增地址信息失败",integer);
        }
    }

    //    修改地址信息
    @PostMapping("/updateaddressinfo")
    public Result updateAddressInfo(@RequestBody AddressDTO addressDTO){
        Integer integer = userService.updateAddressInfo(addressDTO);
        if (integer == 1){
            return Result.success("修改地址信息成功",integer);
        }else {
            return Result.fail("修改地址信息失败",integer);
        }
    }

    //    删除地址信息
    @PostMapping("/deleteaddressinfo")
    public Result deleteAddressInfo(@RequestParam("addressId")Integer addressId){
        Integer integer = userService.deleteAddressInfo(addressId);
        if (integer == 1){
            return Result.success("删除地址信息成功",integer);
        }else {
            return Result.fail("删除地址信息失败",integer);
        }
    }


    //    获取我的订单列表根据条件查询
    @PostMapping("/getmyorderlistbyparam")
    public Result getMyOrderListByParam(@RequestParam("userId")Integer userId,
                                        @RequestParam("orderStatus")Integer orderStatus){
        return Result.success("获取我的订单列表成功",userService.getMyOrderListByParam(userId,orderStatus));
    }

    //    获取订单详情根据订单id
    @PostMapping("/getorderdetailbyorderid")
    public Result getOrderDetailByOrderId(@RequestParam("ordersId")Integer ordersId){
        List<OrderList> orderDetailByOrderId = userService.getOrderDetailByOrderId(ordersId);
        return Result.success("获取订单详情成功",orderDetailByOrderId);
    }

//    生成订单
    @PostMapping("/generateorder")
    public Result generateOrder(@RequestBody GenerateOrderDTO generateOrderDTO) throws InsufficientResourcesException {
        Integer integer = userService.generateOrder(generateOrderDTO);
        if (integer == 1){
            return Result.success("生成订单成功",integer);
        }else {
            return Result.fail("生成订单失败",null);
        }

    }

}
