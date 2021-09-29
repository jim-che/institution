package com.institution.mapper;

import com.institution.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
public interface StudentMapper extends BaseMapper<Student> {
    public List<Student> selectByName(String name);
    public int countClass(long id);
}
