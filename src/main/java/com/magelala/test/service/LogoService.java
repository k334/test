package com.magelala.test.service;

import com.magelala.test.entity.Logo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: shengjun
 * @Date: 2019/10/20 17:01
 */
@Service
@Transactional
public interface LogoService {

    int update(Logo logo);

    int delete(int id);

    Logo getById(int id);

}
