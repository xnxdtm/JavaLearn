package com.dongbao.portal.web.controller.verifyCode;

import com.dongbao.common.base.annotations.TokenCheck;
import com.dongbao.common.base.result.ResultWrapper;
import com.dongbao.portal.web.verifyCode.MyImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * @author zhangjq
 * @version V1.0
 * @Description 使用 JavaSE 实现验证码功能
 */
@RestController
@RequestMapping("/myVerify")
public class MyVerifyCodeController {

    private static final String VERIFY_ATTRIBUTE_NAME = "verifyCode";

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            MyImageCode myImageCode = MyImageCode.getInstance();
            // 缓存图片用于校验
            String code = myImageCode.getCode();
            request.getSession().setAttribute(VERIFY_ATTRIBUTE_NAME, code);
            // 写出图片
            response.setContentType("image/jpeg");
            ByteArrayInputStream imageInput = myImageCode.getImage();
            ServletOutputStream out = response.getOutputStream();
            byte[] bytes = new byte[1024];
            while (imageInput.read(bytes) != -1) {
                out.write(bytes);
            }
        } catch (Exception ignored) {
        }
    }

    @GetMapping("verify")
    @TokenCheck(required = false)
    public ResultWrapper<Object> verify(@RequestParam String code,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        String sessionCode = (String) request.getSession().getAttribute(VERIFY_ATTRIBUTE_NAME);
        if (code.equals(sessionCode)) return ResultWrapper.getSuccessBuilder().msg("验证通过").build();
        else return ResultWrapper.getFailBuilder().msg("验证失败").build();
    }
}
