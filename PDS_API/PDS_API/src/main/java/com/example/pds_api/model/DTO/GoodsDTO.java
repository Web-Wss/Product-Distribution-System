package com.example.pds_api.model.DTO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.example.pds_api.model.GoodsClassification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsDTO {
    @TableId
    private Integer goodsId;//商品id

    private Integer goodsClassificationId;//商品分类id

    private String goodsName;//商品名称

    private BigDecimal goodsPrice;//商品价格

    private BigDecimal goodsOldPrice;//商品原价

    private String goodsCompany;//商品单位

    private Integer goodsTotalInventory;//商品库存

    private Integer remainingInventory;//剩余库存

}
