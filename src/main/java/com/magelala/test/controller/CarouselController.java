package com.magelala.test.controller;

import com.magelala.test.dao.CarouselMapper;
import com.magelala.test.entity.Carousel;
import com.magelala.test.entity.ResResult;
import com.magelala.test.service.CarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/advertise")
@Api(value = "广告管理接口",tags = "动态轮播")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private HttpServletRequest request;

    @Resource
    private CarouselMapper carouselMapper;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<List<Carousel>> all(@RequestParam(value = "limit", required = false) Integer limit,
                                         @RequestParam(value = "page", required = false) Integer page){
        List<Carousel> carousels = carouselMapper.selectPage((page-1)*limit,limit);
        List<Carousel> carouselList = carouselService.all();
        ResResult<List<Carousel>> resResult = new ResResult<>(0,"成功",carousels);
        resResult.setLimit(limit);
        resResult.setPage(page);
        resResult.setCount(carouselList.size());
        return resResult;
    }

    @ApiOperation(value = "添加广告")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Carousel> add(@RequestBody @ApiParam(name = "广告对象",value = "JSON对象") Carousel carousel){
        ResResult<Carousel> carouselResResult = new ResResult<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        carousel.setCreateDate(timestamp);
        String path = (String)request.getSession().getAttribute("path");
        carousel.setImgUrl(path);
        carousel.setSort(carousel.getId());
        carouselService.save(carousel);
        carouselResResult.setData(carousel);
        carouselResResult.setCode(200);
        carouselResResult.setCount(1);
        carouselResResult.setMsg("添加成功");
        return carouselResResult;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Carousel> delete(@RequestParam("id") Integer id){
        Carousel carousel = carouselService.getById(id);
        ResResult<Carousel> resResult = new ResResult<>();
        if (null != carousel){
            carouselService.delete(id);
            resResult.setCount(200);
            resResult.setData(carousel);
            resResult.setMsg("删除成功");
        }else{
            resResult.setCode(-1);
            resResult.setData(null);
            resResult.setMsg("删除失败");
        }
        return resResult;
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<Carousel>> findById(@RequestParam(value = "id",required = true) Integer id){
        Carousel carousel = carouselService.getById(id);
        List<Carousel> list = new ArrayList<>();
        ResResult<List<Carousel>> resResult = new ResResult<>();
        if (null != carousel){
            resResult.setCode(0);
            resResult.setCount(1);
            list.add(carousel);
            resResult.setData(list);
        }else{
            resResult.setCount(0);
            resResult.setData(null);
        }
        return resResult;
    }

    @ApiOperation(value = "模糊查询",notes = "根据标题查询广告")
    @RequestMapping(value = "/finds",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<List<Carousel>> findByTitle(@RequestParam(value = "title") String title){
        int end = title.indexOf(",");
        List<Carousel> list = carouselService.getCarouselByTitle(title.substring(0,end).trim());
        ResResult<List<Carousel>> resResult = new ResResult<>();
        resResult.setCode(0);
        resResult.setCount(list.size());
        resResult.setPage(1);
        resResult.setData(list);
        resResult.setMsg("成功");
        resResult.setLimit(list.size());
        return resResult;
    }

    //----------------------------------------需求未完善-------------------------------------------------------

    @RequestMapping(value = "deletes",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<List<Carousel>> batchDelete(Integer[] ids){
        List<Carousel> list = carouselMapper.deleteList(ids);
        ResResult<List<Carousel>> resResult = new ResResult<>();
        resResult.setCount(list.size());
        resResult.setCode(0);
        resResult.setData(list);
        return resResult;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<Carousel> update(@RequestBody Carousel carousel){
        ResResult<Carousel> resResult = new ResResult<>();
        Carousel carousel1 = carouselService.getById(carousel.getId());
        if (null != carousel1){
            String path = (String)request.getSession().getAttribute("path");
            resResult.setCode(0);
            resResult.setMsg("修改成功");
            carousel.setImgUrl(path);
            carouselService.update(carousel);
            resResult.setData(carousel);
            request.getSession().setAttribute("path",null);
        }else{
            resResult.setCode(-1);
            resResult.setMsg("修改失败");
            resResult.setData(null);
        }
        return resResult;
    }


    //-------------------------------------------------------------------------------------------------

    @ApiIgnore
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "carousel/add";
    }

    @ApiIgnore
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id, Model model){
        Carousel carousel = carouselService.getById(id);
        model.addAttribute("carousel",carousel);
        return "carousel/update";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ApiIgnore
    public String carousel(){
        return "carousel";
    }

}
