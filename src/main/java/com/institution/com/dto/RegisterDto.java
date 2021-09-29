package com.institution.com.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author chenguo
 */
@Data
public class RegisterDto {
    @NotBlank(message = "姓名不能为空")
    private String name;

    private Integer type;
    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password;

    private String id_number;

    private String phone;

    private String avatar;

    @Email(message = "请输入正确的邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

}
