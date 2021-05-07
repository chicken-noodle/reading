package com.example.reading.controller;

import com.example.reading.dao.*;
import com.example.reading.entities.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    private FileMp3Mapper fileMp3Mapper;

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

    @RequestMapping("/login_check")
    public String loginCheck(String username, String password, HttpServletRequest request, Model model) {
        List<User> users = userMapper.lognkCheck(username, password);
        if (users.size() < 1) {
            model.addAttribute("message", "用户名或密码错误");
            return "login";
        } else {
            request.getSession().setAttribute("user", username);
            return "index";
        }
    }

    @ResponseBody
    @RequestMapping("/get_info")
    public String getInfo(HttpServletRequest request) {
        String username = request.getSession().getAttribute("user") == null ? "" : request.getSession().getAttribute("user").toString();
        if (username.equals("")) {
            return "";
        }
        User user = userMapper.selectByUsername(username);
        return user.toString();
    }

    @ResponseBody
    @RequestMapping("/post_topic")
    public String postTopic(Integer id, String title, String username) {
        Topic topic = new Topic();
        topic.setTopic_title(title);
        topic.setUsername(username);
        topicMapper.insertSelective(topic);
        int max_id = topicMapper.selectMaxId();
        mapper.insertPoemTopicRelation(id, max_id);
        return "true";
    }

    @ResponseBody
    @RequestMapping("/post_reply")
    public String postReply(Integer id, String title, String username) {
        Reply reply = new Reply();
        reply.setText(title);
        reply.setBright("0");
        reply.setLike(0);
        reply.setUsername(username);
        replyMapper.insert(reply);
        int max_id = replyMapper.selectMaxId();
        mapper.insertTopicReplyRelation(id, max_id);
        return "true";
    }

    @RequestMapping("/register_check")
    public String registerCheck(User user, Model model) {

        int ans = userMapper.registerCheck(user);
        if (ans > 0) {
            model.addAttribute("tip", "账号已存在，请重新注册");
            return "register";
        }

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
        return poem.getGuide_lang();
    }

    @ResponseBody
    @RequestMapping("/selectWork2")
    public String selectWork2(Integer id) {
        Poem poem = poemMapper.selectByPrimaryKey(id);
        return poem.getText();
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
    public String postSmallReply(Integer id, String title, String username) {
        SmallReply smallReply = new SmallReply();
        smallReply.setText(title);
        smallReply.setUsername(username);
        smallReply.setReply_id(id);
        mapper.insertSmallReply(smallReply);
        return "true";
    }

    @ResponseBody
    @RequestMapping("/give_like")
    public String giveLike(Integer id, Integer num) {
        mapper.giveLike(num, id);
        return "true";
    }

    @ResponseBody
    @RequestMapping("/get_related_mp3")
    public String getRelatedMp3(Integer id) {
        List<Integer> l = mapper.relatedmp3(id);
        StringBuilder ans = new StringBuilder("[");
        for (int i = 0; i < l.size(); i++) {
            if (i == 0) {
                ans.append(l.get(i));
            } else {
                ans.append(",").append(l.get(i));
            }
        }
        ans.append("]");
        return ans.toString();
    }

    @ResponseBody
    @RequestMapping("/get_related_mp4")
    public String getRelatedMp4(Integer id) {
        List<Integer> ll = mapper.relatedmp4(id);
        StringBuilder ans2 = new StringBuilder("[");
        for (int i = 0; i < ll.size(); i++) {
            if (i == 0) {
                ans2.append(ll.get(i));
            } else {
                ans2.append(",").append(ll.get(i));
            }
        }
        ans2.append("]");
        return ans2.toString();
    }

    @ResponseBody
    @PostMapping("/upload")
    public void upload(@RequestParam(value = "file", required = true) MultipartFile file,@RequestParam(value = "poem_id", required = true)Integer poem_id, HttpServletRequest request) throws Exception {
        String username = request.getSession().getAttribute("user") == null ? "" : request.getSession().getAttribute("user").toString();
        Integer id = 0;
        if(!username.equals("")){
            id = userMapper.selectByUsername(username).getId();
            file.transferTo(new File("D:/reading_read/"+id+"_"+poem_id+".mp3"));

            Integer ans = fileMp3Mapper.countByUserId(id,poem_id);
            if(ans!=null){

            }else{
                FileMp3 fileMp3 = new FileMp3();
                fileMp3.setPoem_id(poem_id);
                fileMp3.setUser_id(id);
                fileMp3.setUsername(username);
                fileMp3Mapper.insertSelective(fileMp3);
            }
        }else{

        }
    }

    @RequestMapping("/listen_other")
    public String listenOther(Integer id,Model model){
        List<Map<String,Object>> lll = mapper.relatedReading(id);
        StringBuilder ans3 = new StringBuilder("[");
        int len = lll.size();
        for(int j=0;j<len;j++){
            ans3.append("{");
            ans3.append("\"username\"");
            ans3.append(":");
            ans3.append("\"").append(lll.get(j).get("username")).append("\"");
            ans3.append(",");
            ans3.append("\"user_id\"");
            ans3.append(":");
            ans3.append("\"").append(lll.get(j).get("user_id")).append("\"");
            ans3.append("}").append(",");
        }
        ans3.deleteCharAt(ans3.length()-1);
        ans3.append("]");
        model.addAttribute("ans3",ans3);
        return "listen";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "login";
    }


}
