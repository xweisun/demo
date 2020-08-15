package com.sun.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.entity.Employee;
import com.sun.mapper.EmployeeMapper;
import com.sun.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements TestService {
    /**
     * 测试测试
     */
    @Autowired
    EmployeeMapper employeeMapper;

    public void test() {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID().variant());
        employee.setLastName("too");
        employee.setAge(12);
        employee.setGender(0);
        employee.setEmail("qqq.com");
        employeeMapper.insert(employee);
    }
}
