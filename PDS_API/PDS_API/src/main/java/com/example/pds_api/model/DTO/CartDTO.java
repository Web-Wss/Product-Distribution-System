package com.example.pds_api.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {

    private Integer cartId;//购物车id

    private Integer goodsNumber;//商品数量

    private Integer goodsSelectedStatus;//商品选中状态

}
