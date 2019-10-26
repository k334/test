package com.magelala.test.service.impl;

import com.magelala.test.dao.LogoMapper;
import com.magelala.test.entity.Logo;
import com.magelala.test.entity.Nav;
import com.magelala.test.service.LogoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: shengjun
 * @Date: 2019/10/20 17:03
 */
@Service
@Transactional
public class LogoServiceImpl implements LogoService {

    @Resource
    private LogoMapper logoMapper;

    @Override
    public int update(Logo logo) {
        return logoMapper.updateById(logo);
    }

    @Override
    public int delete(int id) {
        return logoMapper.deleteById(id);
    }

    @Override
    public Logo getById(int id) {
        return logoMapper.selectById(id);
    }
}
