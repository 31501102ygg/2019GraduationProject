package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;

import java.text.ParseException;
import java.util.List;

public interface MovieService {
    Movie addMovie(MovieDto movieDto);

    boolean uploadMovieImg(MovieDto movieDto);

    List<Movie> searchMovie(MovieDto movieDto);

    ResultDto updateMovieInfo(MovieDto movieDto) throws ParseException;

    List<Movie> searchNewestMovie();
}
