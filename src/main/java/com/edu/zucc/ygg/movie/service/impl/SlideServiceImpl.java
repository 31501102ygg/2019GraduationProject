package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.domain.Slide;
import com.edu.zucc.ygg.movie.service.SlideService;
import org.springframework.stereotype.Service;

@Service
public class SlideServiceImpl extends BaseService<Slide> implements SlideService {
    @Override
    public Slide slideAdd(Slide slide) {
        return insert(slide);
    }

    @Override
    public Slide slideUpdate(Slide slide) {
        return updateSelective(slide);
    }

    @Override
    public Integer slideDelete(Integer slideId) {
        return delete((long)slideId);
    }
}
