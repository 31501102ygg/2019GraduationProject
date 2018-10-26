package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.SRegion;

import java.util.List;

public interface SRegionService extends Service<SRegion> {

    SRegion getByID(Integer id);

    List<SRegion> list(int i);
}
