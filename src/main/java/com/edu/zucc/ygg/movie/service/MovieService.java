package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;

public interface MovieService {
    Movie addMovie(MovieDto movieDto);

    boolean uploadMovieImg(MovieDto movieDto);
}
