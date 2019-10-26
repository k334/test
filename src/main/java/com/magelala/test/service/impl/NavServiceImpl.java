package com.magelala.test.service.impl;

import com.magelala.test.dao.NavMapper;
import com.magelala.test.entity.Nav;
import com.magelala.test.service.NavService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: shengjun
 * @Date: 2019/10/19 14:46
 */
@Service
@Transactional
public class NavServiceImpl implements NavService {

    @Resource
    private NavMapper navMapper;

    @Override
    public int addParentNav(Nav nav) {
        return navMapper.insert(nav);
    }

    @Override
    public int addSubNav(Nav nav,int id) {
        return navMapper.addSubNav(nav,id);
    }

    @Override
    public int deleteById(int id) {
        return navMapper.deleteById(id);
    }

    @Override
    public int deletes(Integer[] ids) {
        return navMapper.deletes(ids);
    }

    @Override
    public int updateById(Nav nav) {
        return navMapper.updateById(nav);
    }

    @Override
    public List<Nav> allParentNav() {
        return navMapper.allParentNav();
    }

    @Override
    public List<Nav> allSubNav(Integer pid) {
        return navMapper.allSubNav(pid);
    }

    @Override
    public Nav getById(int id) {
        return navMapper.getById(id);
    }

    @Override
    public int max() {
        return navMapper.max();
    }

    @Override
    public int maxSub(int pid) {
        return navMapper.maxSub(pid);
    }

    @Override
    public Nav up(int sort) {
        return navMapper.upNav(sort);
    }

    @Override
    public Nav upSub(int sort, int pid) {
        return navMapper.upSubNav(sort,pid);
    }

    @Override
    public Nav down(int sort) {
        return navMapper.downNav(sort);
    }

    @Override
    public Nav downSub(int sort, int pid) {
        return navMapper.downSubNav(sort,pid);
    }

    @Override
    public void updateSelf(int id, int sort) {
        navMapper.updateSelfSort(id,sort);
    }

    @Override
    public void updateSubSelf(int id, int sort, int pid) {
        navMapper.updateSubSelfSort(id,sort,pid);
    }

    @Override
    public String selectTitle(int pid) {
        return navMapper.title(pid);
    }

}
