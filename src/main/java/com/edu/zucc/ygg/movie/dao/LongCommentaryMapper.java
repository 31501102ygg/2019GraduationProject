package com.edu.zucc.ygg.movie.dao;

import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LongCommentaryMapper extends MyMapper<LongCommentary> {

    public List<LongCommentary> getLongCommentaryList();
}