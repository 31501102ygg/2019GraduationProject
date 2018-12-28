package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.ShortCommentary;
import com.edu.zucc.ygg.movie.dto.ShortCommentaryDto;

import java.util.List;

public interface ShortCommentaryService {
    ShortCommentary addShortCommentary(ShortCommentary shortCommentary);

    ShortCommentary getShortCommentary(Integer shortCommentaryId);

    ShortCommentary searchShortCommentary(Integer userId, Integer movieId);

    List<ShortCommentary> searchShortCommentaryListByMovie(Integer movieId);

    List<ShortCommentary> searchShortCommentaryListByUser(Integer userId);

    List<ShortCommentaryDto> fuzzySearchShortCommentaryListByUser(String userName, String movieName);

    int updateShortCommentary(ShortCommentary shortCommentary);

    Integer deleteShortCommentary(Integer shortCommentaryId);

    boolean removeShortCommentary(Integer shortCommentaryId);
}
