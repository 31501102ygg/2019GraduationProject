package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.SlideMapper;
import com.edu.zucc.ygg.movie.domain.Slide;
import com.edu.zucc.ygg.movie.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideServiceImpl extends BaseService<Slide> implements SlideService {
    @Autowired
    SlideMapper slideMapper;

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

    @Override
    public List<Slide> getSlideList() {
        return slideMapper.getSlideList();
    }

    @Override
    public List<Slide> searchSlideList(String title) {
        return slideMapper.searchSlideList(title);
    }
}
