package com.dongbao.portal.web.verifyCode;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author zhangjq
 * @version V1.0
 * @Description JavaSE 画一个验证码
 */
@Data
public class MyImageCode {

    private String code;
    private ByteArrayInputStream image;

    public static MyImageCode getInstance() throws IOException {
        return new MyImageCode();
    }

    private int width = 400;
    private int height = 100;

    private MyImageCode() throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.getGraphics();
        // 绘画背景
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, width, height);
        // 绘画 6 位随机数
        graphics.setFont(new Font("宋体", Font.PLAIN, 30));
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            String val = String.valueOf(random.nextInt(10));
            sb.append(val);
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawString(val, (width / 6) * i + 20, 40);
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine((width / 6) * i, 40, (width / 6) * i + 25, 40 - 30);
        }
        graphics.setColor(new Color(100, 100, 100));
        // 加线条打乱验证码图片
        for (int i=0;i<100;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x,y,x+x1,y+y1);
        }
        graphics.dispose();
        // 设置code/image属性
        ByteOutputStream outputStream = new ByteOutputStream();
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        ImageIO.write(image, "jpeg", imageOutputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        this.code = sb.toString();
        this.image = inputStream;
    }
}
