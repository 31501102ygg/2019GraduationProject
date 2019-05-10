package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MovieMapper extends MyMapper<Movie> {
    List<Movie> movieSearch(MovieDto movieDto);

    List<Movie> newestMovieList();

    List<Movie> hotMovieList();

    Movie getMovieInfo(@Param("id")Integer id);

    void updateReadNumber(@Param("id")int id,@Param("count1")int count1);

    List<Map<String,Integer>> getMovieRead();

    void updateScore(@Param("id")int id,@Param("score")double score);
}