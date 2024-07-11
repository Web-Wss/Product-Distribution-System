package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pds_api.mapper.UserMapper;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.User;
import com.example.pds_api.service.LoginService;
import com.example.pds_api.utils.JWTUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService{

    @Override
    public HashMap<String, Object> userLogin(LoginDTO loginDTO) {
        HashMap<String, Object> map = new HashMap<>();
        String username = loginDTO.getUsername();
        String phone = loginDTO.getPhone();
//        根据手机号查询数据库是否有该用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        queryWrapper1.eq("username",username)
                .eq("phone",phone);
        List<User> users = this.list(queryWrapper);
//        判断是否有该用户
        if (users.size() == 0){
//            没有该用户，进行注册
            User user = new User();
            user.setUsername(username);
            user.setPhone(phone);
            boolean insert = this.save(user);
//            注册成功
            if (insert){
                user = this.getOne(queryWrapper);
                String token = JWTUtils.generateToken(username);
                map.put("token", token);
                map.put("user", user);
                return map;
            }
        }
        if (users.size() == 1){
//            有该用户，判断登录信息
            User user = this.getOne(queryWrapper1);
            if (user != null){
                String token = JWTUtils.generateToken(username);
                map.put("token", token);
                map.put("user", user);
                return map;
            }else{
                return null;
            }
        }
        return null;
    }
}
