package com.example.pds_api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.AddressMapper;
import com.example.pds_api.model.Address;
import com.example.pds_api.model.DTO.AddressDTO;
import com.example.pds_api.model.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AddressMapper addressMapper;

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

}
