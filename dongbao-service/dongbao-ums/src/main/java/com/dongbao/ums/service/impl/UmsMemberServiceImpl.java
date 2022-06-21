package com.dongbao.ums.service.impl;

import com.dongbao.ums.entity.UmsMember;
import com.dongbao.ums.entity.dto.UmsMemberRegisterParamDTO;
import com.dongbao.ums.mapper.UmsMemberMapper;
import com.dongbao.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.DoubleToIntFunction;

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

    @Override
    public void hello() {
        System.out.println("serviceImpl Hello");
    }

    @Override
    public String register(UmsMemberRegisterParamDTO umsMemberRegisterParamDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParamDTO, umsMember);
        umsMemberMapper.insert(umsMember);
        return "success";
    }
}
