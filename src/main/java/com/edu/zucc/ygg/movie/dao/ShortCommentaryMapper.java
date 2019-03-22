package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.ShortCommentary;
import com.edu.zucc.ygg.movie.dto.ShortCommentaryDto;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortCommentaryMapper extends MyMapper<ShortCommentary> {
    ShortCommentary searchShortCommentary(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    List<ShortCommentary> searchShortCommmentaryListByMovie(@Param("movieId") Integer movieId);

    List<ShortCommentary> searchShortCommmentaryListByUser(@Param("userId") Integer userId);

    List<ShortCommentaryDto> fuzzySearchShortCommentary(@Param("username") String username, @Param("movieName") String movieName);

    @Select("select count(0) from short_commentary where movieId = #{movieId} and userId = #{userId}")
    public int checkExist(@Param("movieId")int movieId,@Param("userId")int userId);
}