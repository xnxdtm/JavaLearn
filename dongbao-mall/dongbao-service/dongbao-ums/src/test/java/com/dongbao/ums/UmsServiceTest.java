package com.dongbao.ums;

import com.dongbao.ums.service.UmsMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UmsServiceTest {

    @Autowired
    UmsMemberService umsMemberService;

    @Test
    void testServer(){
        umsMemberService.hello();
    }

}
