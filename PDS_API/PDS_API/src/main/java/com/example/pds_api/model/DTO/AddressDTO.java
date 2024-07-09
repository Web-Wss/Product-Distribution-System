package com.example.pds_api.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    private Integer addressId;//地址id

    private Integer userId;//用户id

    private String addressInfo;//地址信息

    private String contacts;//联系人

    private String phone;//手机号

    private Integer isDefault;//是否默认

}
