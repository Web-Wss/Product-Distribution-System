package com.example.pds_api.controller;

import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Result;
import com.example.pds_api.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class LoginController {


    @Resource
    private LoginService loginService;

    //    用户登录
    @PostMapping("/userlogin")
    public Result userLogin(@RequestBody LoginDTO loginDTO){
        HashMap<String, Object> hashMap = loginService.userLogin(loginDTO);
        if (hashMap == null){
            return Result.fail("用户名或密码有误，请重新登录",null);
        }
        return Result.success("登录成功!",hashMap);
    }

//    分销商登录
    @PostMapping("/distributorlogin")
    public Result login(@RequestBody LoginDTO loginDTO) {
        HashMap<String, Object> login = loginService.distributorLogin(loginDTO);
        if (login == null) {
            return Result.fail("登录失败");
        }
        return Result.success("登录成功",login);
    }


    //    管理员登录
    @PostMapping("/administratorslogin")
    public Result login(@RequestParam("phone")String phone,
                        @RequestParam("password")String password) {
        HashMap<String, Object> login = loginService.administratorsLogin(phone, password);
        return Result.success("管理员登录",login);
    }



}
