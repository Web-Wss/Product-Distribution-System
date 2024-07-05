package com.example.pds_api.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddGoodsToCartDTO {

    private Integer userId;//用户id

    private Integer goodsId;//商品id

    private Integer goodsNumber;//商品数量

    private Integer remainingInventory;//剩余库存

}
