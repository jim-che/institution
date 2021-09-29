package com.institution.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    private Integer type;
    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password;

    private String idNumber;

    private String phone;

    private Integer status;

    private String avatar;

    @Email(message = "请输入正确的邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private LocalDateTime gmtCreate;
}
