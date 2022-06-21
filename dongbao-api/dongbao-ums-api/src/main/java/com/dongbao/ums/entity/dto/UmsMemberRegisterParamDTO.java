package com.dongbao.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.ums.entity.dto
 * @Version 1.0
 */
@Data
@ToString
public class UmsMemberRegisterParamDTO {
    private String username;
    private String password;
    private String icon;
    private String email;
    private String nickName;
}
