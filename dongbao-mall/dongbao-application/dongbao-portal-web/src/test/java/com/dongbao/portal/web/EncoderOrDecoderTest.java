package com.dongbao.portal.web;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.portal.web
 * @Version 1.0
 */
public class EncoderOrDecoderTest {
    @Test
    void testMD5() {
        String source = "12345";
        String md5Encryption = DigestUtils.md5DigestAsHex(source.getBytes());
        System.out.println("第一次MD5加密的结果为：" + md5Encryption);
        String md5Encryption2 = DigestUtils.md5DigestAsHex(source.getBytes());
        System.out.println("第二次MD5加密的结果为：" + md5Encryption2);
    }

    @Test
    void testBcrypt() {
        String source = "12345";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptEncryption = bCryptPasswordEncoder.encode(source);
        System.out.println("第一次bCrypt加密的结果为：" + bCryptEncryption);
        String bCryptEncryption2 = bCryptPasswordEncoder.encode(source);
        System.out.println("第二次bCrypt加密的结果为：" + bCryptEncryption2);

        // 验证
        boolean matches1 = bCryptPasswordEncoder.matches(source, bCryptEncryption);
        System.out.println("第一次加密的校验结果：" + matches1);
        boolean matches2 = bCryptPasswordEncoder.matches(source, bCryptEncryption2);
        System.out.println("第二次加密的校验结果：" + matches2);
        boolean matches3 = bCryptPasswordEncoder.matches("abcdef", bCryptEncryption2);
        System.out.println("第三次加密的校验结果，应该是不正确：" + matches3);
    }
}
