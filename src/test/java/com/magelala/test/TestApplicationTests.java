package com.magelala.test;

import com.magelala.test.dao.CarouselMapper;
import com.magelala.test.entity.Carousel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Resource
    private CarouselMapper carouselMapper;

    @Test
    public void contextLoads() {
        //sort值为1的下一条记录
        Carousel carousel = carouselMapper.downCarousel(1);
        System.out.println(carousel);

        //sort值为2的上一条记录
        Carousel carousel1 = carouselMapper.upCarousel(2);
        System.out.println(carousel1);

        //更新id为2的sort值为2
        carouselMapper.updateSelfSort(2,2);

        //更新id为32的sort值为32
        carouselMapper.updateSelfSort(32,32);

        //将记录sort为2的sort值修改为1
        carouselMapper.updateOtherSort(1,2);

        List<Carousel> list = carouselMapper.all();
        for (Carousel c: list){
            System.out.println(c);
        }

        System.out.println("===============================================================");

        System.out.println(carouselMapper.maxEntity());
    }

    @Test
    public void testDelete(){
        Integer[] arr = {52,53};
        int i = carouselMapper.deleteList(arr);
        System.out.println(i);
    }

}
