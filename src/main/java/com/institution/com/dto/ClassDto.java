package com.institution.com.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author chenguo
 */
@Data
public class ClassDto {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer capacity;

    private String teacherName;

    private Integer count;

}
