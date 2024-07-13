package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Administrators;
import com.example.pds_api.model.Goods;
import com.example.pds_api.model.VO.DistributorVO;

import java.util.List;

public interface AdministratorsMapper extends BaseMapper<Administrators> {
    Integer getDailyOrderTotalPrice(String s);

    Integer getOrderTotalPrice();

    Goods getGoodsInfoById(Integer goodsId);

    Integer getGoodsOutNumber(Integer goodsId);

    List<DistributorVO> getDistributorRanking();

//    查询分销商总销售额
    Integer getDistributorTotalOrderAmount(Integer distributorId);
}
