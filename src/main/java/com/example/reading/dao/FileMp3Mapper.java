package com.example.reading.dao;

import com.example.reading.entities.FileMp3;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMp3Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileMp3 record);

    int insertSelective(FileMp3 record);

    FileMp3 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileMp3 record);

    int updateByPrimaryKey(FileMp3 record);
}