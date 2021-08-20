package com.institution.controller;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.institution.com.lang.Result;
import com.institution.entity.Admin;
import com.institution.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @RequestMapping("/all")
    public Result test(){
        Admin admin = adminService.getById(1L);
        return Result.success(admin);
    }
}
