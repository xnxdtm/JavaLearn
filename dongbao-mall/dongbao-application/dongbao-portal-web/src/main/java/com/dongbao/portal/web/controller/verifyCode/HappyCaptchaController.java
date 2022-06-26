package com.dongbao.portal.web.controller.verifyCode;

import com.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
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
@RequestMapping("/happy-captcha")
public class HappyCaptchaController {

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
                .style(CaptchaStyle.ANIM)  // 图片是否闪动
                .type(CaptchaType.ARITHMETIC)  // 图片内容(运算/中文/数字等)
                .build().finish();
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        boolean aBoolean = HappyCaptcha.verification(request, verifyCode, true);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "通过";
        }
        return "不通过";
    }
}
