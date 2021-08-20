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

    private Long classId;

    private Long teacherId;

    private Long courseId;

    private Long place;

    private String time;


}
