package com.institution.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.institution.com.dto.ClassDto;
import com.institution.com.lang.Result;
import com.institution.entity.Class;
import com.institution.entity.Teacher;
import com.institution.mapper.ClassMapper;
import com.institution.service.ClassService;
import com.institution.service.StudentService;
import com.institution.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;
    @Resource
    ClassMapper classMapper;
    @Resource
    StudentService studentService;
    @GetMapping("/view")
    public Result blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) {
            currentPage = 1;
        }

        Page page = new Page(currentPage, 7);
        List<ClassDto> list = classMapper.selectJoinList(ClassDto.class, new MPJLambdaWrapper<Class>().select(Class::getId,Class::getName, Class::getCapacity)
                .selectAs(Teacher::getName, ClassDto::getTeacherName)
                .innerJoin(Teacher.class, Teacher::getId, Class::getTeacherId));
        for (ClassDto classDto : list) {
            classDto.setCount(studentService.countClass(classDto.getId()));
        }
        IPage<ClassDto> pageData = classMapper.selectJoinPage(page, ClassDto.class,
                        new MPJLambdaWrapper<Class>().select(Class::getId,Class::getName, Class::getCapacity)
                                .selectAs(Teacher::getName, ClassDto::getTeacherName)
                                .innerJoin(Teacher.class, Teacher::getId, Class::getTeacherId))
                .setRecords(list);
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
        IPage pageData = classService.page(page, new QueryWrapper<Class>().likeRight("name", name).orderByDesc("id"));

        return Result.success(pageData);
    }

//    @PostMapping("/save")
//    public Result save(@RequestBody Teacher teacher){
//        List<Class> teachers = classService.list();
//        for (Class c : teachers) {
//            if(c.getName().equals(teacher.getName())){
//                return Result.failed("课程已被添加");
//            }
//            if(c.getPhone().equals(teacher.getPhone())){
//                return Result.failed("电话已被注册");
//            }
//            if(c.getEmail().equals(teacher.getEmail())){
//                return Result.failed("电子邮箱已被注册");
//            }
//        }
//        System.out.println(teacher);
//        ClassService.save(teacher);
//        return Result.success("新增成功");
//    }


}
