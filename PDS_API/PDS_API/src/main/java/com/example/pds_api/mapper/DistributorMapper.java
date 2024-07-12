package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Distributor;

public interface DistributorMapper extends BaseMapper<Distributor> {

//    获取每日订单总额
    Integer getDailyOrderTotalPrice(Object param);

}
