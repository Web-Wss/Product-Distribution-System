package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.UserMapper;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.User;
import com.example.pds_api.service.LoginService;
import com.example.pds_api.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

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
        List<User> users = userMapper.selectList(queryWrapper);
//        判断是否有该用户
        if (users.size() == 0){
//            没有该用户，进行注册
            User user = new User();
            user.setUsername(username);
            user.setPhone(phone);
            int insert = userMapper.insert(user);
//            注册成功
            if (insert == 1){
                user = userMapper.selectOne(queryWrapper);
                String token = JWTUtils.generateToken(username);
                map.put("token", token);
                map.put("user", user);
                return map;
            }
        }
        if (users.size() == 1){
//            有该用户，判断登录信息
            User user = userMapper.selectOne(queryWrapper1);
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
