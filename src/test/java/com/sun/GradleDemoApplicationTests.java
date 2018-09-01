package com.sun;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.entity.Employee;
import com.sun.mapper.EmployeeMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradleDemoApplicationTests {

    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler.class");


    @Qualifier("dataSource")
	@Autowired
	private DataSource dataSource;

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Test
	public void selectemps(){

        List<Employee> selectemps = employeeMapper.selectemps();
        System.out.println("selectemps = " + selectemps);
    }

	@Test
	public void update(){
        Employee emp = new Employee();
        emp.setId(7);
        emp.setLastName("测试");
        //emp.setAge(90);
        emp.setGender(0);
        emp.setEmail("530562500@qq.com");
        //List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 4), null);

        Page<Employee> employeePage = new Page<>(2, 3);
        //employeePage.setRecords(employeeMapper.selectPage(employeePage, new EntityWrapper<Employee>().eq("gender", 0)));

        List<Employee> emplyees = employeeMapper.getEmplyees(emp,employeePage);
        Employee employee = employeeMapper.selectOne(emp);
        System.out.println("employee = " + employee);
        /*employeeMapper.update(emp,new EntityWrapper<Employee>()
                .eq("last_name","White")
                .eq("gender","0"));*/
    }


	@Test
	public void insert() throws SQLException {


        Employee emp = new Employee();
        emp.setLastName("ddd111");
        emp.setAge(40);
        emp.setEmail("530@qq.com");
        //emp.setSalary(66d);
        Integer insert = employeeMapper.insert(emp);

        Integer id = emp.getId();
        System.out.println("id = " + id);
       // logger.info("s");

        //Employee employee = employeeMapper.selectById("1");
		//System.out.println(employee);
	}

}
