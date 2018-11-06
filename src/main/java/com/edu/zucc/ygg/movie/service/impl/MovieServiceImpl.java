package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.MovieMapper;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.service.MovieService;
import com.edu.zucc.ygg.movie.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class MovieServiceImpl extends BaseService<Movie> implements MovieService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    MovieMapper movieMapper;

    @Override
    public Movie addMovie(MovieDto movieDto) {
        Movie addMovie = new Movie(movieDto);
        try{
            addMovie.setReleaseTime(DateUtil.convertToDate(movieDto.getReleaseTime()));
        } catch (ParseException e) {
            LOGGER.error("日期格式转化失败");
            return null;
        }
        insertSelective(addMovie);
        return addMovie;
    }

    @Override
    public boolean uploadMovieImg(MovieDto movieDto) {
        Movie updateMovie = new Movie(movieDto);
        Movie temp = updateSelective(updateMovie);
        if (temp !=null)
            return true;
        return false;
    }
}
