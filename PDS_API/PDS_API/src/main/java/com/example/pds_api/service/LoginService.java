package com.example.pds_api.service;

import com.example.pds_api.model.DTO.LoginDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface LoginService {

    //    用户登录
    HashMap<String, Object> userLogin(@RequestBody LoginDTO loginDTO);

//    分销商登录
    HashMap<String, Object> distributorLogin(LoginDTO loginDTO);

    //    管理员登录
    HashMap<String, Object> administratorsLogin(String phone, String password);
}
