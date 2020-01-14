package com.liran.community.Mapper;

import com.liran.community.dto.QuestionDTO;
import com.liran.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) "+
            "values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{i},#{size}")
    List<Question> list(Integer i,Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{i},#{size}")
    List<Question> listUserById(Integer userId, Integer i, Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(Integer id);
}
