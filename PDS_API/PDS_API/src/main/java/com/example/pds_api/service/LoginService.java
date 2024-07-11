package com.example.pds_api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

public interface LoginService extends IService<User> {

//    用户登录
    HashMap<String, Object> userLogin(@RequestBody LoginDTO loginDTO);

}
