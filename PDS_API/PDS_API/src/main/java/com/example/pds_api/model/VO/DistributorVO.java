package com.example.pds_api.model.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistributorVO {

    private Integer distributorId;//分销商id

    private Integer totalOrderAmount;//订单总金额

}
