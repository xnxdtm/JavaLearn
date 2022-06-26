package com.dongbao.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dongbao.common.base.JwtUtil;
import com.dongbao.common.base.enums.StateCodeEnum;
import com.dongbao.common.base.result.ResultWrapper;
import com.dongbao.ums.entity.UmsMember;
import com.dongbao.ums.entity.dto.UmsMemberEditParamDTO;
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
    public ResultWrapper<Object> register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO, umsMember);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = bCryptPasswordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(passwordEncode);
        umsMemberMapper.insert(umsMember);
        return ResultWrapper.getSuccessBuilder().build();
    }

    @Override
    public ResultWrapper<Object> login(UmsMemberLoginParamDTO umsMemberLoginParamDTO) {
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(umsMemberLoginParamDTO.getUsername());
        QueryWrapper<UmsMember> wrapper = new QueryWrapper<>(umsMember);
        UmsMember dbUmsMember = umsMemberMapper.selectOne(wrapper);
        if (null == dbUmsMember) {
            return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_NO_EXIST.getCode())
                    .msg(StateCodeEnum.USER_NO_EXIST.getMsg()).build();
        }
        if (!passwordEncoder.matches(umsMemberLoginParamDTO.getPassword(), dbUmsMember.getPassword())) {
            return ResultWrapper.getFailBuilder().code(StateCodeEnum.USER_PASS_ERROR.getCode())
                    .msg(StateCodeEnum.USER_PASS_ERROR.getMsg()).build();
        }
        String token = JwtUtil.createToken(dbUmsMember.getUsername());
        return ResultWrapper.getSuccessBuilder().data(token).build();
    }

    @Override
    public ResultWrapper<Object> editMember(UmsMemberEditParamDTO umsMemberEditParamDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberEditParamDTO, umsMember);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = bCryptPasswordEncoder.encode(umsMember.getPassword());
        umsMember.setPassword(passwordEncode);
        umsMemberMapper.updateById(umsMember);
        return ResultWrapper.getSuccessBuilder().build();
    }
}
