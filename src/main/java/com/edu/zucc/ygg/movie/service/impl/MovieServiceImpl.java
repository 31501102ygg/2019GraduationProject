package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.LongCommentaryMapper;
import com.edu.zucc.ygg.movie.dao.MovieMapper;
import com.edu.zucc.ygg.movie.dao.ShortCommentaryMapper;
import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.dto.MovieDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.service.MovieService;
import com.edu.zucc.ygg.movie.util.DateUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class MovieServiceImpl extends BaseService<Movie> implements MovieService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    MovieMapper movieMapper;
    @Autowired
    LongCommentaryMapper longCommentaryMapper;
    @Autowired
    ShortCommentaryMapper shortCommentaryMapper;

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

    @Override
    public List<Movie> searchMovie(MovieDto movieDto) {
        return movieMapper.movieSearch(movieDto);
    }

    @Override
    public ResultDto updateMovieInfo(MovieDto movieDto) throws ParseException {
        Movie movie = movieDto.convertToMovie();
        if(movieMapper.updateByPrimaryKeySelective(movie) > 0){
            return ResultDtoFactory.toAck("电影信息更新成功");
        }
        else{
            return ResultDtoFactory.toNack("电影信息没有改动");
        }
    }

    @Override
    public List<Movie> searchNewestMovie() {
        List<Movie> movies = movieMapper.newestMovieList();
        return movies;
    }

    @Override
    public List<Movie> searchHotMovie() {
        List<Movie> movies = movieMapper.hotMovieList();
        return movies;
    }

    @Override
    public Movie getMovieInfo(Integer movieId) {
        return movieMapper.getMovieInfo(movieId);
    }

    @Override
    public void checkUserOperation(Integer movieId, Integer userId,MovieDto movieDto) {
        if (longCommentaryMapper.checkExist(movieId,userId)>0){
            movieDto.setLongCommentary(true);
        }else{
            movieDto.setLongCommentary(false);
        }
        if (shortCommentaryMapper.checkExist(movieId,userId)>0){
            movieDto.setShortCommentary(true);
        }else{
            movieDto.setShortCommentary(false);
        }
    }
}
