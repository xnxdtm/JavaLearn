package com.dongbao.portal.web.controller;

import com.dongbao.common.base.result.ResultWrapper;
import com.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.portal.web.controller
 * @Version 1.0
 */
@RestController
@RequestMapping("ums")
public class UmsMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("register")
    public ResultWrapper<Object> register(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        return umsMemberService.register(umsMemberRegisterParamDTO);
    }

    @PostMapping("login")
    public ResultWrapper<Object> login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        return umsMemberService.login(umsMemberLoginParamDTO);
    }
}
