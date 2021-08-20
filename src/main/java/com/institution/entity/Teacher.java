package com.institution.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    private String name;

    private Integer type;

    private String username;

    private String password;

    private String idNumber;

    private String phone;

    private Integer status;

    private String avatar;

    private String email;

    private LocalDateTime gmtCreate;


}
