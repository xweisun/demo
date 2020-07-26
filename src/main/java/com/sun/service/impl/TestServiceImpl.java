package com.sun.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.entity.Employee;
import com.sun.mapper.EmployeeMapper;
import com.sun.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements TestService {
}
