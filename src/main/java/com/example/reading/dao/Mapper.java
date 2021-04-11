package com.example.reading.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    @Select("select topic_id from relation_poem_topic where poem_id = #{poem_id}")
    public List<Integer> relation_poem_topic(Integer poem_id);

    @Select("select reply_id from relation_topic_reply where topic_id = #{topic_id}")
    public List<Integer> relation_topic_reply(Integer topic_id);
}
