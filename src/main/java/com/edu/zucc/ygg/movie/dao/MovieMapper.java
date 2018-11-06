package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieMapper extends MyMapper<Movie> {
}