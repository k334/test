package com.magelala.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.magelala.test.dao.UserMapper;
import com.magelala.test.entity.Author;
import com.magelala.test.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public int add(Author author) {
        int i = userMapper.insert(author);
        return i;
    }

    @Override
    public int delete(int id) {
        int i = userMapper.deleteById(id);
        return i;
    }

    @Override
    public Author find(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public void update(Author author) {
        userMapper.updateById(author);
    }

    @Override
    public List<Author> all() {
        return userMapper.selectList(null);
    }

}
