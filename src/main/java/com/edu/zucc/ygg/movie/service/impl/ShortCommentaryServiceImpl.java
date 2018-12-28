package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.ShortCommentaryMapper;
import com.edu.zucc.ygg.movie.domain.ShortCommentary;
import com.edu.zucc.ygg.movie.dto.ShortCommentaryDto;
import com.edu.zucc.ygg.movie.service.ShortCommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShortCommentaryServiceImpl extends BaseService<ShortCommentary> implements ShortCommentaryService {
    @Autowired
    ShortCommentaryMapper shortCommentaryMapper;

    @Override
    public ShortCommentary addShortCommentary(ShortCommentary shortCommentary) {
        return insert(shortCommentary);
    }

    @Override
    public ShortCommentary getShortCommentary(Integer shortCommentaryId) {
        return shortCommentaryMapper.selectByPrimaryKey(shortCommentaryId);
    }

    @Override
    public ShortCommentary searchShortCommentary(Integer userId, Integer movieId) {
        return shortCommentaryMapper.searchShortCommentary(userId,movieId);
    }

    @Override
    public int updateShortCommentary(ShortCommentary shortCommentary) {
        return shortCommentaryMapper.updateByPrimaryKeySelective(shortCommentary);
    }

    @Override
    public List<ShortCommentary> searchShortCommentaryListByMovie(Integer movieId) {
        return shortCommentaryMapper.searchShortCommmentaryListByMovie(movieId);
    }

    @Override
    public List<ShortCommentary> searchShortCommentaryListByUser(Integer userId) {
        return shortCommentaryMapper.searchShortCommmentaryListByUser(userId);
    }

    @Override
    public List<ShortCommentaryDto> fuzzySearchShortCommentaryListByUser(String userName, String movieName) {
        return shortCommentaryMapper.fuzzySearchShortCommentary(userName,movieName);
    }

    @Override
    public Integer deleteShortCommentary(Integer shortCommentaryId) {
        return shortCommentaryMapper.deleteByPrimaryKey(shortCommentaryId);
    }

    @Override
    public boolean removeShortCommentary(Integer shortCommentaryId) {
        ShortCommentary updateShortCommentary = new ShortCommentary();
        updateShortCommentary.setId(shortCommentaryId);
        updateShortCommentary.setRemoveDate(new Date());
        int updateNum = updateShortCommentary(updateShortCommentary);
        if (updateNum == 1)
            return true;
        return false;
    }
}
