package com.institution.service.impl;

import com.institution.entity.Admin;
import com.institution.mapper.AdminMapper;
import com.institution.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
