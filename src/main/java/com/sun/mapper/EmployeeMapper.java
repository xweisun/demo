package com.sun.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface EmployeeMapper extends BaseMapper<Employee> {

        @Select("SELECT id AS id,last_name AS lastName1,email as email1,gender as gender1," +
            "age as age1  FROM tbl_employee where gender = #{gender}")
        /*@ResultMap("com.sun.mapper.EmployeeMapper.selectEmployeeResultMap")*/
       // @ResultMap("selectEmployeeResultMap")
     List<Employee> getEmplyees(Employee employee,Page<Employee> page);

    List<Employee> selectemps();
}
