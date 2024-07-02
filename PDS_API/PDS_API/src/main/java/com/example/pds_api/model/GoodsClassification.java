package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("goods_classification")
public class GoodsClassification {
    @TableId
    private Integer goodsClassificationId;//分类id

    private String goodsClassificationName;//分类名称

}
