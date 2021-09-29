package com.institution.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Arrangement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long class_id;

    private Long teacher_id;

    private Long course_id;

    private Long place;

    private String time;
}
