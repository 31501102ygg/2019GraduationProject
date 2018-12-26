package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.Slide;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideMapper extends MyMapper<Slide> {
    List<Slide> getSlideList();

    List<Slide> searchSlideList(@Param("title")String title);
}