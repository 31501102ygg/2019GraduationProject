package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieMapper extends MyMapper<Movie> {
    List<Movie> movieSearch(MovieDto movieDto);

    List<Movie> newestMovieList();

    List<Movie> hotMovieList();

    Movie getMovieInfo(@Param("id")Integer id);
}