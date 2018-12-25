package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.Slide;

public interface SlideService {
    Slide slideAdd(Slide slide);

    Slide slideUpdate(Slide slide);

    Integer slideDelete(Integer slideId);
}
