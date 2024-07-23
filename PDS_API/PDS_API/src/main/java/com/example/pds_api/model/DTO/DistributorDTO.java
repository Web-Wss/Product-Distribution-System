package com.example.pds_api.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DistributorDTO {

    private Integer distributorId;//分销商id

    private String nickname;//昵称

    private String phone;//手机号

    private String password;//密码

    private Integer commissionRate;//佣金比例

    private BigDecimal alreadyWithdrawn;//已提现金额

}
