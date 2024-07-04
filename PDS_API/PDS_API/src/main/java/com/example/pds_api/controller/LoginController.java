package com.example.pds_api.controller;

import com.example.pds_api.mapper.UserMapper;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Result;
import com.example.pds_api.model.User;
import com.example.pds_api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
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


}
