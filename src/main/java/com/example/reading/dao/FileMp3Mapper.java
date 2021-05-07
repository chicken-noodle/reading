package com.example.reading.dao;

import com.example.reading.entities.FileMp3;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMp3Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileMp3 record);

    int insertSelective(FileMp3 record);

    FileMp3 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileMp3 record);

    int updateByPrimaryKey(FileMp3 record);

    @Select("select id from file_mp3 where user_id = #{user_id} and poem_id = #{poem_id}")
    Integer countByUserId(@Param("user_id") Integer user_id,@Param("poem_id") Integer poem_id);
}