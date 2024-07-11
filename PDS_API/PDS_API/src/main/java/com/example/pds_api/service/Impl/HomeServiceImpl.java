package com.example.pds_api.service.Impl;

import com.example.pds_api.mapper.GoodsClassificationMapper;
import com.example.pds_api.mapper.GoodsMapper;
import com.example.pds_api.mapper.NoticeMapper;
import com.example.pds_api.model.Goods;
import com.example.pds_api.model.GoodsClassification;
import com.example.pds_api.model.Notice;
import com.example.pds_api.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private GoodsClassificationMapper goodsClassificationMapper;
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Notice getNotice() {
        return noticeMapper.selectById(1);
    }

    @Override
    public List<GoodsClassification> getGoodsClassificationList() {
        return goodsClassificationMapper.selectList(null);
    }

    @Override
    public List<Goods> getGoodsList(Integer classificationId) {
        List<Goods> goodsList;
        if (classificationId == 0) {
            goodsList = goodsMapper.selectGoodsWithInventory();
        }else{
            goodsList = goodsMapper.selectGoodsWithInventoryByClassification(classificationId);
        }
        return goodsList;
    }

    @Override
    public Goods getGoodsDetailById(Integer goodsId) {
        return goodsMapper.selectGoodsWithInventoryById(goodsId);
    }

    @Override
    public List<Goods> searchGoods(String goodsName) {
        return goodsMapper.searchGoods("%" + goodsName + "%");
    }
}
