package com.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;

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
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
}
