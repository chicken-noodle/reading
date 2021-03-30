package com.example.reading.controller;

import com.example.reading.dao.UserMapper;
import com.example.reading.entities.User;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/classic")
    public String classic(){
        return "classic";
    }

    @RequestMapping("/login_check")
    public String loginCheck(String username, String password,HttpServletRequest request){
        List<User> users = userMapper.lognkCheck(username,password);
        if(users.size()<1){
            return "login";
        }else{
            request.getSession().setAttribute("user",username);
            return "index";
        }
    }

    @RequestMapping("/register_check")
    public String registerCheck(User user){
        userMapper.insertSelective(user);
        return "login";
    }


}
