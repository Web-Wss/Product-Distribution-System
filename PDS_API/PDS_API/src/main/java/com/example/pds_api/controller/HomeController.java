package com.example.pds_api.controller;

import com.example.pds_api.mapper.GoodsClassificationMapper;
import com.example.pds_api.mapper.NoticeMapper;
import com.example.pds_api.model.GoodsClassification;
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
    @Resource
    private GoodsClassificationMapper goodsClassificationMapper;


    //    获取通知栏信息
    @GetMapping("/getnotice")
    public Result getNotice(){
        Notice notice = noticeMapper.selectById(1);
        return Result.success("获取通知信息",notice);
    }

//    获取产品分类列表
    @GetMapping("/getgoodsclassificationlist")
    public Result getGoodsClassificationList(){
        List<GoodsClassification> goodsClassifications = goodsClassificationMapper.selectList(null);
        System.out.println("goodsClassifications:"+goodsClassifications);
        return Result.success("获取产品分类列表",goodsClassifications);
    }


}
