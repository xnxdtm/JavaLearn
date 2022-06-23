package com.dongbao.portal.web.controller.verifyCode;

import com.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangjq
 * @version 1.0
 * @Description com.dongbao.portal.web.controller.verifyCode
 */
@RestController
@RequestMapping("/easy-captcha")
public class EasyCaptchaController {
    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 验证码类型: ArithmeticCaptcha ChineseCaptcha ChineseGifCaptcha GifCaptcha SpecCaptcha
            ChineseCaptcha chineseCaptcha = new ChineseCaptcha(150, 50);
            chineseCaptcha.setLen(3);
            CaptchaUtil.out(chineseCaptcha, request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
        boolean aBoolean = CaptchaUtil.ver(verifyCode, request);
        if (aBoolean) {
            HappyCaptcha.remove(request);
            return "通过";
        }
        return "不通过";
    }
}
