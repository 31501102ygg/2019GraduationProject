package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;

import java.util.List;

public interface LongCommentaryService {
    public LongCommentary add(LongCommentary longCommentary);

    public void filterContent(LongCommentary longCommentary);

    public List<LongCommentaryDto> getLongCommentaryList();
}
