package com.example.reading.dao;

import com.example.reading.entities.Topic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}