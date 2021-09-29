package com.institution.service;

import com.institution.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
public interface StudentService extends IService<Student> {
    public List<Student> selectByName(String name);
    public int countClass(long id);
}
