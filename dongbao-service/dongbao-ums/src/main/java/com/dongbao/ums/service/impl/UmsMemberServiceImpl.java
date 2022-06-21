package com.dongbao.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongbao.ums.entity.UmsMember;
import com.dongbao.ums.entity.dto.UmsMemberLoginParamDTO;
import com.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.dongbao.ums.mapper.UmsMemberMapper;
import com.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author xnxdtm
 * @since 2022-06-19
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    UmsMemberMapper umsMemberMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void hello() {
        System.out.println("serviceImpl Hello");
    }

    @Override
    public String register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO, umsMember);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = bCryptPasswordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(passwordEncode);
        umsMemberMapper.insert(umsMember);
        return "success";
    }

    @Override
    public String login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(umsMemberLoginParamDTO.getUsername());
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>(umsMember);
        UmsMember dbUmsMember = umsMemberMapper.selectOne(wrapper);
        if (null == dbUmsMember) {
            return "用户不存在";
        }
        if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(), dbUmsMember.getPassword())) {
            return "用户密码错误";
        }
        return "登录成功";
    }
}
