package com.dongbao.portal.web.controller;

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
    public String register(@RequestBody UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        umsMemberService.register(umsMemberRegisterParamDTO);
        return "register";
    }

    @PostMapping("login")
    public String login(@RequestBody UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        String res = umsMemberService.login(umsMemberLoginParamDTO);
        return res;
    }
}
