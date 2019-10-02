package com.magelala.test.controller;

import com.magelala.test.dao.UserMapper;
import com.magelala.test.entity.Author;
import com.magelala.test.entity.ResResult;
import com.magelala.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseBody
    public ResResult<List<Author>> user(){
        List<Author> authorList = userService.all();
        ResResult<List<Author>> resResult = ResResult.ok(authorList);
        resResult.setCount(authorList.size());
        return resResult;
    }

    //分页查询
    @GetMapping("/findPage")
    @ResponseBody
    public ResResult<List<Author>> findByPage(int limit,int page){
        List<Author> authors = userMapper.selectPage((page-1)*limit,limit);
        List<Author> allAthors = userService.all();
        if (authors!=null){
            ResResult<List<Author>> resResult = new ResResult<>(0,"成功",authors);
            resResult.setLimit(limit);
            resResult.setPage(page);
            resResult.setCount(allAthors.size());
            return resResult;
        }
        return null;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResResult<Author> delete(@RequestParam("id") int id){
        Author author = userService.find(id);
        if (author.getId() == id){
            userService.delete(id);
            ResResult<Author> authorResResult = new ResResult<>();
            authorResResult.setCode(0);
            authorResResult.setData(author);
            return authorResResult;
        }else{
            return ResResult.fail(-1);
        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResResult<Author> add(Author author){
        ResResult<Author> resResult = new ResResult<>();
        author.setArticleNum(0);
        resResult.setCode(0);
        userService.add(author);
        resResult.setCount(1);
        resResult.setMsg("添加成功!");
        resResult.setData(author);
        return resResult;
    }

    @GetMapping("/test")
    public String user(Model model){
        model.addAttribute("authorList",userService.all());
        return "test";
    }
}
