package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Administrators;
import com.example.pds_api.model.Goods;
import com.example.pds_api.model.VO.DistributorVO;

import java.util.List;

public interface AdministratorsMapper extends BaseMapper<Administrators> {

    Integer getDailyOrderTotalPrice(String s);

    Object getOrderTotalPrice();

    Goods getGoodsInfoById(Integer goodsId);

    Integer getDistributorTotalOrderAmount(Integer distributorId);

    List<DistributorVO> getDistributorRanking();

}
