package com.dongbao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dongbao"})
@MapperScan("com.dongbao")
public class DongbaoGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DongbaoGeneratorApplication.class, args);
    }

}
