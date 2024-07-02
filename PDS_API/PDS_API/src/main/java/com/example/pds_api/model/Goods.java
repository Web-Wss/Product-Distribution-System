package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("goods")
public class Goods {
    @TableId
    private Integer goodsId;//商品id

    private Integer goodsClassificationId;//商品分类id

    private String goodsName;//商品名称

    private BigDecimal goodsPrice;//商品价格

    private BigDecimal goodsOldPrice;//商品原价

    private String goodsCompany;//商品单位

    private Integer goodsTotalInventory;//商品库存

    private String goodsImg;//商品图片

    private String goodsVideo;//商品视频

    private Integer goodsStatus;//商品状态

    private String createTime;//创建时间

    private Integer isDelete;//是否删除

}
