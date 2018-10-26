package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.SRegion;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SRegionMapper extends MyMapper<SRegion> {
    List<SRegion> list(int i);
}