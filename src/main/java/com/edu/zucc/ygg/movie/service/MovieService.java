package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface MovieService {
    Movie addMovie(MovieDto movieDto);

    boolean uploadMovieImg(MovieDto movieDto);

    List<Movie> searchMovie(MovieDto movieDto);

    ResultDto updateMovieInfo(MovieDto movieDto) throws ParseException;

    List<Movie> searchNewestMovie();

    List<Movie> searchHotMovie();

    Movie getMovieInfo(Integer movieId);

    void checkUserOperation(Integer movieId,Integer userId,MovieDto movieDto);

    void moviePageReadNumUpdate(int movieId,int count);

    List<Map<String,Integer>> getMovieReadNum();

    void updateMovieScore(int id,double score);

    void delete(int id);
}
