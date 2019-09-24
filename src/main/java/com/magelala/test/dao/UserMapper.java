package com.magelala.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.magelala.test.entity.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<Author> {

    //分页查询，按照升序排序，根据两个请求参数指定，page:当前页,limit:限制一页显示多少条数据
    @Select("select * from author" + " order by id asc limit #{limit},#{page}")
    List<Author> selectPage(@Param("limit") int limit,@Param("page") int page);

}
