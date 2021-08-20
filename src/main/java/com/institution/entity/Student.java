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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    private Integer sex;

    private LocalDateTime birth;

    private String phone;

    private Long classId;

    private Integer isDelete;

    private LocalDateTime gmtCreate;


}
