package com.magelala.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.magelala.test.entity.Carousel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarouselMapper extends BaseMapper<Carousel> {

    //分页查询，按照升序排序，根据两个请求参数指定，page:当前页,limit:限制一页显示多少条数据
    @Select("select * from carousel" + " order by id asc limit #{limit},#{page}")
    List<Carousel> selectPage(@Param("limit") int limit, @Param("page") int page);

    //按照标题进行模糊搜索
    @Select("select * from carousel where title like concat('%',#{title},'%')")
    List<Carousel> selectTitle(@Param("title") String title);

    //批量删除
    List<Carousel> deleteList(@Param("ids") Integer[] ids);

}
