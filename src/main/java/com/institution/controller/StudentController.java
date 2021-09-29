package com.institution.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.institution.com.dto.StudentDto;
import com.institution.com.lang.Result;
import com.institution.entity.Student;
import com.institution.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chengguo
 * @since 2021-08-20
 */
@RestController
@RequestMapping("/student")
@RequiresAuthentication
public class StudentController {
    @Autowired
    StudentService studentService;
    @RequestMapping("/getAll")
    public Result getAll(){
        List<Student> students = studentService.list();
        return getResult(students);
    }

    @PostMapping("/delete")
    public Result doDelete(@Validated Long id){
        System.out.println("************");
        studentService.removeById(id);
        System.out.println("------"+id+"------");
        return Result.success("删除成功");
    }

    @GetMapping("/students")
    public Result blogs(Integer currentPage) {
        if(currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 7);
        IPage pageData = studentService.page(page, new QueryWrapper<Student>().orderByDesc("gmt_create"));
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
        IPage pageData = studentService.page(page, new QueryWrapper<Student>().likeRight("name", name).orderByDesc("gmt_create"));

        return Result.success(pageData);
    }

    @PostMapping("/save")
    public Result save(@RequestBody StudentDto studentDto){
        List<Student> list = studentService.list();
        for (Student student : list) {
            if(student.getName().equals(studentDto.getName())){
                return Result.failed("姓名重复！");
            }
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        System.out.println(student);
        studentService.save(student);
        return Result.success("新增成功");
    }

    @PostMapping("/update")
    public Result update(@RequestBody StudentDto studentDto){
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        System.out.println(student);
        studentService.updateById(student);
        return Result.success("修改成功");
    }

    private Result getResult(List<Student> students) {
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            BeanUtils.copyProperties(student, studentDto);
            studentDtos.add(studentDto);
        }
        Map<String, List> map = new HashMap<>();
        map.put("studentInfo", studentDtos);
        return Result.success(map);
    }

}
