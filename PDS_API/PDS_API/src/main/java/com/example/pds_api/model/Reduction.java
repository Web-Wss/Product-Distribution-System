package com.example.pds_api.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("reduction")
public class Reduction {
    @TableId(type = IdType.AUTO)
    private Integer fullReductionId;//满减id

    private BigDecimal fullConditionPrice;//满减价格

    private BigDecimal fullReductionAmount;//满减价格

}
