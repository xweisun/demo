package com.sun.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sun.entity.Employee;
import com.sun.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sunxw on 2018-08-23 18:15
 */

public class TestService {
    @Autowired
    EmployeeMapper employeeMapper;


    public void testEntityWrapperDelete() {

        employeeMapper.delete(
                new EntityWrapper<Employee>()
                        .eq("last_name", "Tom")
                        .eq("age", 22)
        );
    }

}
