package com.example.reading.controller;

import com.example.reading.dao.*;
import com.example.reading.entities.Poem;
import com.example.reading.entities.Reply;
import com.example.reading.entities.Topic;
import com.example.reading.entities.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    @Resource
    private UserMapper userMapper;


    @Resource
    private PoemMapper poemMapper;

    @Resource
    private TopicMapper topicMapper;

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private Mapper mapper;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/classic")
    public String classic() {
        return "classic";
    }

    @RequestMapping("/reply")
    public String reply() {
        return "reply";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/login_check")
    public String loginCheck(String username, String password, HttpServletRequest request) {
        List<User> users = userMapper.lognkCheck(username, password);
        if (users.size() < 1) {
            return "login";
        } else {
            request.getSession().setAttribute("user", username);
            return "index";
        }
    }

    @RequestMapping("/register_check")
    public String registerCheck(User user) {
        userMapper.insertSelective(user);
        return "login";
    }


    @ResponseBody
    @RequestMapping("/workList")
    public String workList() {
        List<Poem> l = new ArrayList<>();
        l = poemMapper.selectAllWork();
        return l.toString();
    }

    @ResponseBody
    @RequestMapping("/selectWork")
    public String selectWork(Integer id) {
        Poem poem = poemMapper.selectByPrimaryKey(id);
        return poem.toString();
    }

    @ResponseBody
    @RequestMapping("/selectTopic")
    public String selectTopic(Integer id) {
        //查询关联的话题id
        List<Integer> ll = new ArrayList<>();
        ll = mapper.relation_poem_topic(id);
        List<Topic> l = new ArrayList<>();
        int len = ll.size();
        for (int i = 0; i < len; i++) {
            Topic topic = topicMapper.selectByPrimaryKey(ll.get(i));
            l.add(topic);
        }
        return l.toString();
    }

    @ResponseBody
    @RequestMapping("/selectReply")
    public String selectReply(Integer id) {
        //查询关联的话题id
        List<Integer> ll = new ArrayList<>();
        //获得关联回复的id列表
        ll = mapper.relation_topic_reply(id);
        //接收回复的列表
        List<Reply> l = new ArrayList<>();
        //id列表长度
        int len = ll.size();

        for (int i = 0; i < len; i++) {
            Reply reply = replyMapper.selectByPrimaryKey(ll.get(i));
            l.add(reply);
        }

        return l.toString();
    }


}
