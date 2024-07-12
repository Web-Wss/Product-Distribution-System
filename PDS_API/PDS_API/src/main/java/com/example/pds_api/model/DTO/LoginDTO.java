package com.example.pds_api.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {

    private String username;//用户名
    private String phone;//手机号
    private String password;//密码
    private String newPassword;//新密码
    private String newDPassword;//确认新密码

}
