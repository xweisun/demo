package com.sun.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sun.entity.Employee;
import com.sun.mapper.EmployeeMapper;
import com.sun.model.JsonResult;
import com.sun.model.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sunxw on 2018-08-24 11:08
 */
@Api(value = "测试demo", description = "测试demo")
@RestController
@RequestMapping("/test")
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;


    @ApiOperation(value = "测试demo")
    @GetMapping(value = "/demo", produces = "application/json;charset=utf-8")
    public JsonResult getRooms() {
        Employee employee = new Employee();
        employee.setLastName("too");
        employee.setAge(12);
        employee.setGender(0);
        employeeMapper.insert(employee);
        return ResultUtil.error(200,"test");
    }

    @ApiOperation(value = "测试demo")
    @GetMapping(value = "/demo2", produces = "application/json;charset=utf-8")
    public JsonResult getRooms2() {
        Employee employee = new Employee();
        employee.setLastName("too");
        employee.setAge(12);
        employee.setGender(0);
        Wrapper<Employee> employeeWrapper = new EntityWrapper<Employee>().eq("gender", 0);
//        List<Employee> employees = employeeMapper.selectList(employeeWrapper);
        List<Employee> employees = employeeMapper.selectemps();
        List<Employee> employees1 = employeeMapper.selectList(employeeWrapper);
        return ResultUtil.success(employees);
    }
}
