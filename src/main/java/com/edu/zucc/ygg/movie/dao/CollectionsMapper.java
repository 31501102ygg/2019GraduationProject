package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.Collections;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionsMapper extends MyMapper<Collections> {
    @Select("select count(1) from collections where commentary_id = #{id} and user_id = #{userId}")
    int exist(int userId,int id);
}