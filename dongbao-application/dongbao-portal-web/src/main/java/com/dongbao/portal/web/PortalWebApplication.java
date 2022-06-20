package com.dongbao.portal.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.dongbao"})
@MapperScan("com.dongbao.ums.mapper.xml")
public class PortalWebApplication{

    public static void main(String[] args) {
        SpringApplication.run(PortalWebApplication.class, args);
    }

}