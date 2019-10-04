package com.magelala.test.service.impl;

import com.magelala.test.dao.CarouselMapper;
import com.magelala.test.entity.Carousel;
import com.magelala.test.service.CarouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {

    @Resource
    private CarouselMapper carouselMapper;

    @Override
    public Carousel getById(Integer id) {
        return carouselMapper.selectById(id);
    }

    @Override
    public int save(Carousel carousel) {
        return carouselMapper.insert(carousel);
    }

    @Override
    public int delete(Integer id) {
        return carouselMapper.deleteById(id);
    }

    @Override
    public int update(Carousel carousel) {
        return carouselMapper.updateById(carousel);
    }

    @Override
    public List<Carousel> all() {
        return carouselMapper.selectList(null);
    }

    @Override
    public List<Carousel> getCarouselByTitle(String title) {
        return carouselMapper.selectTitle(title);
    }
}
