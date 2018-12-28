package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.Slide;

import java.util.List;

public interface SlideService {
    Slide slideAdd(Slide slide);

    Slide slideUpdate(Slide slide);

    Integer slideDelete(Integer slideId);

    List<Slide> getSlideList();

    List<Slide> searchSlideList(String title);

    void exchangeShowSlide(Slide oldSlide,Slide newSlide);
}
