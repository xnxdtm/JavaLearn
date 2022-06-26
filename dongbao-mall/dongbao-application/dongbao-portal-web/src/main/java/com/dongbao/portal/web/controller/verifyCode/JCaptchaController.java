package com.dongbao.portal.web.controller.verifyCode;

import com.dongbao.common.base.annotations.TokenCheck;
import com.dongbao.common.base.result.ResultWrapper;
import com.dongbao.portal.web.util.JCaptchaUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author zhangjq
 * @version V1.0
 * @Description JCaptcha第三方依赖生成二维码
 */
@RestController
@RequestMapping("/jCaptcha")
public class JCaptchaController {

    private static final String VERIFY_ATTRIBUTE_NAME = "verifyCode";

    @GetMapping("/generator")
    @TokenCheck(required = false)
    public void generatorVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getSession().getId();
            BufferedImage bufferedImage = JCaptchaUtil.getService().getImageChallengeForID(id);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);
            jpegEncoder.encode(bufferedImage);
            response.setHeader("Cache-Control", "no-store");
            response.setContentType("image/jpeg");
            byte[] bytes = byteArrayOutputStream.toByteArray();
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("verify")
    @TokenCheck(required = false)
    public ResultWrapper<Object> verify(String verifyCode, HttpServletRequest request) {
        Boolean b = JCaptchaUtil.getService().validateResponseForID(request.getSession().getId(), verifyCode);
        if (b) return ResultWrapper.getSuccessBuilder().msg("验证通过").build();
        else return ResultWrapper.getFailBuilder().msg("验证失败").build();
    }
}
