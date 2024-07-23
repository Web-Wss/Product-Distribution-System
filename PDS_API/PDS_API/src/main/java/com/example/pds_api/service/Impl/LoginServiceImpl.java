package com.example.pds_api.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pds_api.mapper.AdministratorsMapper;
import com.example.pds_api.mapper.DistributorMapper;
import com.example.pds_api.mapper.UserMapper;
import com.example.pds_api.model.Administrators;
import com.example.pds_api.model.DTO.LoginDTO;
import com.example.pds_api.model.Distributor;
import com.example.pds_api.model.User;
import com.example.pds_api.service.LoginService;
import com.example.pds_api.utils.JWTUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private UserMapper userMapper;
    @Resource
    private DistributorMapper distributorMapper;
    @Resource
    private AdministratorsMapper administratorsMapper;

    @Override
    public HashMap<String, Object> userLogin(LoginDTO loginDTO) {
        HashMap<String, Object> map = new HashMap<>();
        String username = loginDTO.getUsername();
        String phone = loginDTO.getPhone();
//        根据手机号查询数据库是否有该用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper1 = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        userQueryWrapper1.eq("username",username)
                .eq("phone",phone);
        List<User> users = userMapper.selectList(userQueryWrapper);
//        判断是否有该用户
        if (users.size() == 0){
//            没有该用户，进行注册
            User user = new User();
            user.setUsername(username);
            user.setPhone(phone);
            int insert = userMapper.insert(user);
//            注册成功
            if (insert != 0){
                String token = JWTUtils.generateToken(phone);
                map.put("token", token);
                map.put("user", user);
                map.put("role","user");
                return map;
            }
        }
        if (users.size() == 1){
//            有该用户，判断登录信息
            User user = userMapper.selectOne(userQueryWrapper1);
            if (user != null){
                String token = JWTUtils.generateToken(phone);
                map.put("token", token);
                map.put("user", user);
                map.put("role","user");
                return map;
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public HashMap<String, Object> distributorLogin(LoginDTO loginDTO) {
        HashMap<String, Object> map = new HashMap<>();
        Distributor distributor = new Distributor();
        QueryWrapper<Distributor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginDTO.getPhone())
                .eq("password", loginDTO.getPassword());
        Distributor distributor1 = distributorMapper.selectOne(queryWrapper);
        if (distributor1 != null) {
            String token = JWTUtils.generateToken(loginDTO.getPhone());
            distributor.setDistributorId(distributor1.getDistributorId());
            distributor.setNickname(distributor1.getNickname());
            distributor.setPhone(distributor1.getPhone());
            distributor.setPassword(null);
            distributor.setStatus(distributor1.getStatus());
            distributor.setCreateTime(distributor1.getCreateTime());
            distributor.setCommissionRate(distributor1.getCommissionRate());
            map.put("token", token);
            map.put("distributor", distributor);
            map.put("role", "distributor");
            return map;
        }
        return null;
    }

    @Override
    public HashMap<String, Object> administratorsLogin(String phone, String password) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Administrators> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone)
                .eq("password", password);
        Administrators administrators = administratorsMapper.selectOne(queryWrapper);
        if (administrators != null){
            String token = JWTUtils.generateToken(phone);
            map.put("token", token);
            map.put("administrators", administrators);
            map.put("role", "administrators");
            return map;
        }
        return null;
    }
}
