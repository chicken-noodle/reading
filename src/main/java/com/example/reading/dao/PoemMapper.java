package com.example.reading.dao;

import com.example.reading.entities.Poem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PoemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Poem record);

    int insertSelective(Poem record);

    Poem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Poem record);

    int updateByPrimaryKey(Poem record);

    @Select("select * from poem")
    public List<Poem> selectAllWork();


}