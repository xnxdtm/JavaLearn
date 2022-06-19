package com.dongbao.ums;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dongbao.ums.entity.UmsMember;
import com.dongbao.ums.mapper.UmsMemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMemberTest {

    private static final String MEMBER_NAME = "张三23";

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Test
    void testUmsMemberInsert() {
        UmsMember member = new UmsMember();
        member.setUsername(MEMBER_NAME);
        member.setPassword("123");
        umsMemberMapper.insert(member);
    }

    /* ***************** MybatisPlus 的三种更新方式 ***************** */

    /**
     * 使用LambdaUpdateWrapper更新
     * !!!存在的问题: umsMemberMapper.update(null, updateWrapper); 传入 null 将导致自动填充的更新功能失效
     */
    @Test
    void testUmsMemberUpdate() {
        LambdaUpdateWrapper<UmsMember> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(UmsMember::getUsername, MEMBER_NAME)
                .set(UmsMember::getPassword, "456");
        umsMemberMapper.update(null, updateWrapper);
    }

    /**
     * 使用LambdaUpdateWrapper更新
     * umsMemberMapper.update(member, updateWrapper); 推荐使用 LambdaUpdateWrapper 并同时传入实体
     */
    @Test
    void testUmsMemberUpdate1() {
        UmsMember member = new UmsMember();
        member.setPassword("3456");
        LambdaUpdateWrapper<UmsMember> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(UmsMember::getUsername, MEMBER_NAME);
        umsMemberMapper.update(member, updateWrapper);
    }

    /**
     * 使用UpdateWrapper更新
     */
    @Test
    void testUmsMemberUpdate2() {
        UpdateWrapper<UmsMember> update = Wrappers.update();
        update.eq("username", MEMBER_NAME);
        UmsMember umsMember = new UmsMember();
        umsMember.setPassword("1234");
        umsMemberMapper.update(umsMember, update);
    }
}
