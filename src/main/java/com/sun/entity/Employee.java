package com.sun.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@TableName(value="user")
@Data
@ApiModel("测试显示信息")
public class Employee {


    /*
	 * @TableId:
	 * 	 value: 指定表中的主键列的列名， 如果实体属性名与列名一致，可以省略不指定. 
	 *   type: 指定主键策略. 
	 */
    @ApiModelProperty("ID")
	@TableId(value="id" , type =IdType.AUTO)
	private Integer id ;   //  int 

	@TableField(value = "name")
	private String  lastName;

	private String  email ;
	@TableField(exist = false)
	private Integer gender;

	private Integer age ;
	
	@TableField(exist=false)
	private Double salary ; 

}