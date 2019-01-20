package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.LongCommentary;

public interface LongCommentaryService {
    public LongCommentary add(LongCommentary longCommentary);

    public void filterContent(LongCommentary longCommentary);
}
