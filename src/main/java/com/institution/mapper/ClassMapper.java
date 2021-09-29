package com.institution.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.institution.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
public interface ClassMapper extends MPJBaseMapper<Class> {
    public List<Map<String,Object>> selectAll() throws Exception;
}
