package com.institution.service;

import com.institution.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
public interface ClassService extends IService<Class> {
    public List<Map<String,Object>> selectAll() throws Exception;
}
