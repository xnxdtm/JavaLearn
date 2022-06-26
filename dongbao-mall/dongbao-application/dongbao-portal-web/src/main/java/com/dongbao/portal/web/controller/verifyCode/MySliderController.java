package com.dongbao.portal.web.controller.verifyCode;

import com.dongbao.common.base.annotations.TokenCheck;
import com.dongbao.portal.web.util.SliderUtil;
import com.dongbao.portal.web.util.VerificationVO;
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
@RequestMapping("/my-slider")
public class MySliderController {

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {
        return SliderUtil.cut();
    }

    @GetMapping("/verify")
    @TokenCheck(required = false)
    public String verify(String verifyCode, HttpServletRequest request) {
//		Boolean aBoolean = kaptcha.validate(verifyCode, 10);
//		if (aBoolean) {
//			return "通过";
//		}
        return "不通过";
    }
}