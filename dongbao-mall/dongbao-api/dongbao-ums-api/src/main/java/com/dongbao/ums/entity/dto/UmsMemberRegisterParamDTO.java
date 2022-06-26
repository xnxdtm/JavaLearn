package com.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.ums.entity.dto
 * @Version 1.0
 */
@Data
@ToString
public class UmsMemberRegisterParamDTO {

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 1, max = 8, message = "用户名在1-8之间")
    private String username;
    private String password;
    private String icon;
    @Email
    private String email;
    private String nickName;
}
