package com.example.pds_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pds_api.model.Distributor;

import java.util.HashMap;

public interface DistributorMapper extends BaseMapper<Distributor> {


    //            根据分销商id和日期查询销售额
    Integer getDailyOrderTotalPrice(HashMap<String, Object> param);

}
