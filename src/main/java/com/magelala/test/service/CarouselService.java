package com.magelala.test.service;

import com.magelala.test.entity.Carousel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface CarouselService {

    Carousel getById(Integer id);

    int save(Carousel carousel);

    int delete(Integer id);

    int update(Carousel carousel);

    List<Carousel> all();

    List<Carousel> getCarouselByTitle(String title);

    Carousel up(int sort);

    Carousel down(int sort);

    void updateSelfSort(Integer id,int sort);

    void updateOtherSort(int newSort,int sort);

}
