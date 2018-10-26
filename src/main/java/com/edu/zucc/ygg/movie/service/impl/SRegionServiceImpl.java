package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.constant.CacheConstants;
import com.edu.zucc.ygg.movie.dao.SRegionMapper;
import com.edu.zucc.ygg.movie.domain.SRegion;
import com.edu.zucc.ygg.movie.service.SRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SRegionServiceImpl extends BaseService<SRegion> implements SRegionService {
    @Autowired
    SRegionMapper sRegionMapper;

    @Override
    @Cacheable(value = CacheConstants.REGION, key = "'REGION_'+ #id")
    public SRegion getByID(Integer id) {
        return Optional.ofNullable(sRegionMapper.selectByPrimaryKey(id)).orElse(new SRegion());
    }

    @Override
    public List<SRegion> list(int i) {
        return sRegionMapper.list(i);
    }

}
