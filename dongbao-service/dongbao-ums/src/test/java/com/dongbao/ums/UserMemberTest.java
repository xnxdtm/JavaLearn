package com.dongbao.ums;

import com.dongbao.ums.entity.UmsMember;
import com.dongbao.ums.mapper.UmsMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMemberTest {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Test
    void testUmsMemberInsert() {
        UmsMember member = new UmsMember();
        member.setUsername("张三12");
        member.setPassword("123");
        umsMemberMapper.insert(member);
    }

}
