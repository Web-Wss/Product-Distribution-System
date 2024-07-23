package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("goods_inventory")
public class GoodsInventory {

    @TableId
    private Integer goodsInventoryId;//库存id

    private Integer goodsId;//商品id

    private Integer goodsInventorySum;//库存数量

}
