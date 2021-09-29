package com.institution;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.institution.com.dto.ClassDto;
import com.institution.entity.Student;
import com.institution.entity.Teacher;
import com.institution.mapper.ClassMapper;
import com.institution.service.ClassService;
import com.institution.service.StudentService;
import com.institution.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.institution.entity.Class;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class InstitutionApplicationTests {

    @Autowired
    TeacherService teacherService;
    @Resource
    StudentService studentService;
    @Resource
    ClassService classService;
    @Resource
    ClassMapper classMapper;

    @Test
    void contextLoads() {
        List<Teacher> teachers = teacherService.list();
        for (Teacher teacher : teachers) {
            teacher.setPassword(SecureUtil.md5(teacher.getPassword()));
            teacherService.updateById(teacher);
        }
    }

    @Test
    void test() throws Exception {
        Page page = new Page(1, 7);
        List<ClassDto> list = classMapper.selectJoinList(ClassDto.class, new MPJLambdaWrapper<Class>().select(Class::getId,Class::getName, Class::getCapacity)
                .selectAs(Teacher::getName, ClassDto::getTeacherName)
                .innerJoin(Teacher.class, Teacher::getId, Class::getTeacherId));
        for (ClassDto classDto : list) {
            classDto.setCount(studentService.countClass(classDto.getId()));
        }
        IPage<ClassDto> page1 = classMapper.selectJoinPage(page, ClassDto.class,
                        new MPJLambdaWrapper<Class>().select(Class::getId,Class::getName, Class::getCapacity)
                                .selectAs(Teacher::getName, ClassDto::getTeacherName)
                                .innerJoin(Teacher.class, Teacher::getId, Class::getTeacherId))
                                .setRecords(list);
        System.out.println("************");
        System.out.println(page1.getRecords());
    }

    @Test
    void test2(){
        Student student = new Student();
        student.setId(21L);
        student.setSex(1);
        student.setPhone("1242152524");

        studentService.updateById(student);
    }


}
