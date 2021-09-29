package com.institution.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.institution.com.lang.Result;
import com.institution.entity.Course;
import com.institution.entity.Teacher;
import com.institution.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;


    @GetMapping("/view")
    public Result blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 7);
        IPage pageData = teacherService.page(page, new QueryWrapper<Teacher>().orderByDesc("id"));
        return Result.success(pageData);
    }

    @PostMapping("/search")
    public Result search(@NotBlank(message = "内容不能为空") String name, Integer currentPage){
        if(name==null){
            return Result.failed("查询不能为空");
        }
        if(currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 7);
        IPage pageData = teacherService.page(page, new QueryWrapper<Teacher>().likeRight("name", name).orderByDesc("id"));

        return Result.success(pageData);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Teacher teacher){
        List<Teacher> teachers = teacherService.list();
        for (Teacher c : teachers) {
            if(c.getName().equals(teacher.getName())){
                return Result.failed("课程已被添加");
            }
            if(c.getPhone().equals(teacher.getPhone())){
                return Result.failed("电话已被注册");
            }
            if(c.getEmail().equals(teacher.getEmail())){
                return Result.failed("电子邮箱已被注册");
            }
        }
        System.out.println(teacher);
        teacherService.save(teacher);
        return Result.success("新增成功");
    }

}
