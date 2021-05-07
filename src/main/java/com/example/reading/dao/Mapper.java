package com.example.reading.dao;

import com.example.reading.entities.SmallReply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    @Select("select topic_id from relation_poem_topic where poem_id = #{poem_id}")
    public List<Integer> relation_poem_topic(Integer poem_id);

    @Select("select reply_id from relation_topic_reply where topic_id = #{topic_id}")
    public List<Integer> relation_topic_reply(Integer topic_id);

    @Insert("insert into relation_poem_topic value (#{id},#{max_id})")
    public int insertPoemTopicRelation(@Param("id") Integer id, @Param("max_id") Integer max_id);

    @Insert("insert into relation_topic_reply value (#{id},#{max_id})")
    public int insertTopicReplyRelation(@Param("id") Integer id, @Param("max_id") Integer max_id);

    @Select("select * from small_reply where reply_id = #{reply_id}")
    public List<SmallReply> selectSmallReply(Integer reply_id);

    @Insert("insert into small_reply (`text`,`username`,`reply_id`) values (#{text},#{username},#{reply_id})")
    public int insertSmallReply(SmallReply smallReply);

    @Update("update reply set `like` = `like` + #{num} where id = #{id}")
    public int giveLike(@Param("num") int num, @Param("id") int id);

    @Select("select id from relation_mp3 where poem_id=#{poem_id}")
    public List<Integer> relatedmp3(Integer poem_id);

    @Select("select user_id from file_mp3 where poem_id=#{poem_id}")
    public List<Integer> relatedRead(Integer poem_id);

    @Select("select username from file_mp3 where poem_id=#{poem_id}")
    public List<String> relatedReader(Integer poem_id);

    @Select("select user_id,username from file_mp3 where poem_id=#{poem_id}")
    public List<Map<String,Object>> relatedReading(Integer poem_id);

    @Select("select id from relation_mp4 where poem_id=#{poem_id}")
    public List<Integer> relatedmp4(Integer poem_id);
}
