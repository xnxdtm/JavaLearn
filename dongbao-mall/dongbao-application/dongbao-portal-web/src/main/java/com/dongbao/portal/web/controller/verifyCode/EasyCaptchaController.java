package com.dongbao.portal.web.controller.verifyCode;

import com.dongbao.common.base.annotations.TokenCheck;
import com.ramostear.captcha.HappyCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/generator-redis")
    @TokenCheck(required = false)
    public Map<String, String> generatorCodeRedis(HttpServletRequest request, HttpServletResponse response) {
        SpecCaptcha specCaptcha = new SpecCaptcha(100, 50);
        String text = specCaptcha.text();
        System.out.println("验证码：" + text);
        String sessionId = request.getSession().getId();
        stringRedisTemplate.opsForValue().set(sessionId, text);
        String s1 = specCaptcha.toBase64();
        System.out.println("base64:"+s1);
        Map<String,String> map = new HashMap<>();
        map.put("base64",s1);
        return map;
    }

    @GetMapping("/verify-redis")
    @TokenCheck(required = false)
    public String verifyRedis(String verifyCode, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String c = stringRedisTemplate.opsForValue().get(sessionId);
        if (verifyCode.equals(c)) {
            HappyCaptcha.remove(request);
            return "通过";
        }
        return "不通过";
    }
}
