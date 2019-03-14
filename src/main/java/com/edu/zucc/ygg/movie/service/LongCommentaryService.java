package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;
import com.edu.zucc.ygg.movie.dto.LongCommentarySearchDto;

import java.util.List;

public interface LongCommentaryService {
    public LongCommentary add(LongCommentary longCommentary);

    public void filterContent(LongCommentary longCommentary);

    public List<LongCommentaryDto> getLongCommentaryList();

    public boolean collection(int id,int userId);

    public boolean cancelCollection(int id,int userId);

    public boolean searchLike(int userId,String commentaryId);

    public boolean searchCollections(int userId,int commentaryId);

    public List<LongCommentaryDto> getCollection(int userId);

    public LongCommentaryDto get(int id);

    public List<LongCommentaryDto> search(LongCommentarySearchDto searchDto);

    public boolean delete(int id);

    public long likeNumber(int id);
}
