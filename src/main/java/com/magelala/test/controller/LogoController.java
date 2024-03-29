package com.magelala.test.controller;

import com.magelala.test.entity.Logo;
import com.magelala.test.entity.ResResult;
import com.magelala.test.service.LogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shengjun
 * @Date: 2019/10/20 17:06
 */
@Controller
@RequestMapping("/logo")
public class LogoController {

    @Autowired
    private LogoService logoService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public ResResult<Logo> show(){
        ResResult<Logo> resResult = new ResResult<>();
        Logo logo = logoService.getById(1);
        resResult.setData(logo);
        resResult.setCode(0);
        resResult.setCount(1);
        return resResult;
    }


    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public ResResult<Logo> update(@RequestBody Logo logo){
        ResResult<Logo> resResult = new ResResult<>();
        String path = (String)request.getSession().getAttribute("path");
        logo.setSrc(path);
        request.getSession().setAttribute("path",null);
        logo.setAuthorId(1);
        logo.setAuthorName("admin");
        logoService.update(logo);
        resResult.setCode(200);
        resResult.setCount(1);
        resResult.setData(logo);
        resResult.setMsg("修改logo成功");
        return resResult;
    }

/*
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Logo> delete(Integer id){
        ResResult<Logo> resResult = new ResResult<>();
        Logo logo = logoService.getById(id);
        if (null != logo){
            resResult.setData(logo);
            resResult.setMsg("删除成功");
            resResult.setCount(1);
            resResult.setCode(200);
        }else{
            resResult.setData(null);
            resResult.setMsg("删除失败");
        }
        return resResult;
    }
*/

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        Logo logo = logoService.getById(1);
        model.addAttribute("logo",logo);
        request.getSession().setAttribute("src",logo.getSrc());
        return "logo/logo";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id") Integer id, Model model){
        model.addAttribute("logo",logoService.getById(id));
        return "logo/edit";
    }

}
