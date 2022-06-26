package com.dongbao.portal.web.controller.verifyCode;

import com.baomidou.kaptcha.Kaptcha;
import com.dongbao.common.base.annotations.TokenCheck;
import com.dongbao.portal.web.custom.MyGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangjq
 * @version 1.0
 * @Description com.dongbao.portal.web.controller.verifyCode
 */
@RestController
@RequestMapping("/kcaptcha")
public class KcaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.render();
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {

        Boolean aBoolean = kaptcha.validate(verifyCode, 10);
        if (aBoolean) {
            return "通过";
        }
        return "不通过";
    }

    @Autowired
    MyGoogleKaptcha myGoogleKaptcha;

    @GetMapping("/generator-my")
    @TokenCheck(required = false)
    public void generatorCodeMy(HttpServletRequest request, HttpServletResponse response) {
        myGoogleKaptcha.render();
    }

    @GetMapping("/verify-my")
    @TokenCheck(required = false)
    public String verifyMy(String verifyCode, HttpServletRequest request) {
        Boolean aBoolean = myGoogleKaptcha.validate(verifyCode, 10);
        if (aBoolean) {
            return "通过";
        }
        return "不通过";
    }
}