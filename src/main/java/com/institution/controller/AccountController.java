package com.institution.controller;

import ch.qos.logback.core.recovery.ResilientFileOutputStream;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.institution.com.dto.LoginDto;
import com.institution.com.dto.RegisterDto;
import com.institution.com.lang.Result;
import com.institution.entity.Admin;
import com.institution.entity.Teacher;
import com.institution.service.AdminService;
import com.institution.service.TeacherService;
import com.institution.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import com.institution.com.Algorithm;
/**
 * @author chenguo
 */
@RestController
public class AccountController {
    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginDto loginDto, HttpServletResponse response){
        Admin admin = adminService.getOne(new QueryWrapper<Admin>().eq("username", loginDto.getUsername()));
        Assert.notNull(admin, "用户不存在");
        if(!admin.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.failed("密码错误");
        }
        String jwt = jwtUtils.generateToken(admin.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", admin.getId())
                .put("username", admin.getUsername())
                .put("name", admin.getName())
                .put("avatar", admin.getAvatar())
                .put("email", admin.getEmail())
                .map()
        );
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterDto registerDto, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(registerDto, teacher);
        teacher.setPassword(SecureUtil.md5(teacher.getPassword()));
        List<Teacher> teachers = teacherService.list();
        for (Teacher t : teachers) {
            if(!Algorithm.verifyEmail(teacher.getEmail())){
                return Result.failed("电子邮箱非法");
            }
            if(t.getEmail().equals(teacher.getEmail())){
                return Result.failed("电子邮箱已被使用");
            }
            if(t.getUsername().equals(teacher.getUsername())){
                return Result.failed("用户名已被使用");
            }
            if(t.getPhone().equals(teacher.getPhone())){
                return Result.failed("手机号已被使用");
            }
        }

        teacherService.save(teacher);
        return Result.success(null);
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }


}
