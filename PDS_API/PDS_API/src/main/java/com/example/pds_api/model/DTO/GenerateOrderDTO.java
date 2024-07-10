package com.example.pds_api.model.DTO;


import com.example.pds_api.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenerateOrderDTO {

    private Integer addressId;//地址id
    private Integer distributorId;//分销商id
    private BigDecimal orderActualPayment;//订单实付金额
    private BigDecimal orderDiscountPrice;//订单优惠金额
    private List<Cart> orderGoodsList;//订单商品列表
    private BigDecimal orderTotalPrice;//订单总金额
    private String remark;//备注
    private Integer userId;//用户id

}
