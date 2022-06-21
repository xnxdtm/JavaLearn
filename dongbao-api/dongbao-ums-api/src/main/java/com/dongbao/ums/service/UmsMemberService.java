package com.dongbao.ums.service;

import com.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author xnxdtm
 * @since 2022-06-19
 */
public interface UmsMemberService {

    void hello();

    String register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO);

    String login(UmsMemberLoginParamDTO umsMemberLoginParamDTO);
}
