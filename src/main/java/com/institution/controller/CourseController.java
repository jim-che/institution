package com.institution.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.institution.com.dto.StudentDto;
import com.institution.com.lang.Result;
import com.institution.entity.Course;
import com.institution.entity.Student;
import com.institution.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @GetMapping("/view")
    public Result blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 7);
        IPage pageData = courseService.page(page, new QueryWrapper<Course>().orderByDesc("id"));
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
        IPage pageData = courseService.page(page, new QueryWrapper<Course>().likeRight("name", name).orderByDesc("id"));

        return Result.success(pageData);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Course course){
        List<Course> courses = courseService.list();
        for (Course c : courses) {
            if(c.getName().equals(course.getName())){
                return Result.failed("课程已被添加");
            }
        }
        System.out.println(course);
        courseService.save(course);
        return Result.success("新增成功");
    }
}
