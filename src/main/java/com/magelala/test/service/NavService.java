package com.magelala.test.service;

import com.magelala.test.entity.Nav;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/19 14:45
 */
@Service
@Transactional
public interface NavService {

    int addParentNav(Nav nav);

    int addSubNav(Nav nav,int id);

    int deleteById(int id);

    int deletes(Integer[] ids);

    int updateById(Nav nav);

    List<Nav> allParentNav();

    List<Nav> allSubNav(Integer pid);

    Nav getById(int id);

    int max();

    int maxSub(int pid);

    Nav up(int sort);

    Nav upSub(int sort,int pid);

    Nav down(int sort);

    Nav downSub(int sort,int pid);

    void updateSelf(int id,int sort);

    void updateSubSelf(int id,int sort,int pid);

    String selectTitle(int pid);
}
