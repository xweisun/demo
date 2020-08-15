package com.sun.handler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author cuidalong
 * @Description:
 * @Date 2018/6/14 17:52
 */
@ApiModel("当前页面信息")
@Data
@ToString
public class SessionData implements Serializable {

    private static final long serialVersionUID = 6448595706857217330L;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("全名")
    private String fullName;

    @ApiModelProperty("toonType")
    private String toonType;

    @ApiModelProperty("进入时间")
    private String accessTime;

    @ApiModelProperty("toonName")
    private String toonName;
}
