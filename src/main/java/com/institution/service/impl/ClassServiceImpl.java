package com.institution.service.impl;

import com.institution.entity.Class;
import com.institution.mapper.ClassMapper;
import com.institution.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Resource
    ClassMapper classMapper;
    @Override
    public List<Map<String, Object>> selectAll() throws Exception {
        return classMapper.selectAll();
    }
}
