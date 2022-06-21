package com.dongbao.portal.web.controller;

import com.dongbao.common.base.JwtUtil;
import com.dongbao.common.base.result.ResultWrapper;
import com.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResultWrapper<Object> register(@RequestBody @Valid UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        return umsMemberService.register(umsMemberRegisterParamDTO);
    }

    @PostMapping("login")
    public ResultWrapper<Object> login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        return umsMemberService.login(umsMemberLoginParamDTO);
    }

    @PostMapping("verifyToken")
    public ResultWrapper<Object> verifyToken(@RequestParam String token) {
        String username = JwtUtil.parseToken(token);
        return ResultWrapper.getSuccessBuilder().data(username).build();
    }
}
