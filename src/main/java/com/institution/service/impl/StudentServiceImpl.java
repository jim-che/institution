package com.institution.service.impl;

import com.institution.entity.Student;
import com.institution.mapper.StudentMapper;
import com.institution.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
