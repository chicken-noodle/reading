package com.example.reading.controller;

import com.example.reading.dao.*;
import com.example.reading.entities.*;
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

    @ResponseBody
    @RequestMapping("/get_info")
    public String getInfo(HttpServletRequest request){
        String username = request.getSession().getAttribute("user") == null ? "":request.getSession().getAttribute("user").toString();
        if(username.equals("")){
            return "";
        }
        User user = userMapper.selectByUsername(username);
        return user.toString();
    }

    @ResponseBody
    @RequestMapping("/post_topic")
    public String postTopic(Integer id, String title,String username){
        Topic topic = new Topic();
        topic.setTopic_title(title);
        topic.setUsername(username);
        topicMapper.insertSelective(topic);
        int max_id = topicMapper.selectMaxId();
        mapper.insertPoemTopicRelation(id,max_id);
        return "true";
    }

    @ResponseBody
    @RequestMapping("/post_reply")
    public String postReply(Integer id, String title,String username){
        Reply reply = new Reply();
        reply.setText(title);
        reply.setBright("0");
        reply.setLike(0);
        reply.setUsername(username);
        replyMapper.insert(reply);
        int max_id = replyMapper.selectMaxId();
        mapper.insertTopicReplyRelation(id,max_id);
        return "true";
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

    @ResponseBody
    @RequestMapping("/selectSmallReply")
    public String selectSmallReply(Integer id) {
        //接收小回复的列表
        List<SmallReply> l = new ArrayList<>();
        //id列表长度
        l = mapper.selectSmallReply(id);
        return l.toString();
    }

    @ResponseBody
    @RequestMapping("/post_smallReply")
    public String postSmallReply(Integer id, String title,String username){
        SmallReply smallReply = new SmallReply();
        smallReply.setText(title);
        smallReply.setUsername(username);
        smallReply.setReply_id(id);
        mapper.insertSmallReply(smallReply);
        return "true";
    }

    @ResponseBody
    @RequestMapping("/give_like")
    public String giveLike(Integer id,Integer num){
        mapper.giveLike(num,id);
        return "true";
    }


}
