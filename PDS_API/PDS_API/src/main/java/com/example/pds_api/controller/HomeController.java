package com.example.pds_api.controller;

import com.example.pds_api.mapper.NoticeMapper;
import com.example.pds_api.model.Notice;
import com.example.pds_api.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/home")
//用户端
public class HomeController {

    @Resource
    private NoticeMapper noticeMapper;

//    测试
    @GetMapping("/test")
    public Result test(){
        List<Notice> notices = noticeMapper.selectList(null);
        return Result.success("获取通知信息","notices");
    }


}
