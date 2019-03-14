package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.CollectionsMapper;
import com.edu.zucc.ygg.movie.dao.LongCommentaryMapper;
import com.edu.zucc.ygg.movie.domain.Collections;
import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;
import com.edu.zucc.ygg.movie.dto.LongCommentarySearchDto;
import com.edu.zucc.ygg.movie.service.LongCommentaryService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LongCommentaryServiceImpl extends BaseService<LongCommentary> implements LongCommentaryService {
    @Autowired
    LongCommentaryMapper longCommentaryMapper;
    @Autowired
    CollectionsMapper collectionsMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public LongCommentary add(LongCommentary longCommentary) {
        filterContent(longCommentary);
        return insertSelective(longCommentary);
    }

    @Override
    public void filterContent(LongCommentary longCommentary) {
        StringBuffer filterContent = new StringBuffer();
        StringBuffer pureText = new StringBuffer();
        Document doc = Jsoup.parseBodyFragment(longCommentary.getContent());
        Element body = doc.body();
        Elements pList = body.select("p");
        for (Element element:pList){
            if (element.children().size()==0) {
                filterContent.append(element.toString());
                if (pureText.length()<300)
                    pureText.append(element.text());
            }
        }
        longCommentary.setFilterContent(filterContent.toString());
        longCommentary.setPureText(pureText.toString());
    }

    @Override
    public List<LongCommentaryDto> getLongCommentaryList() {
        return longCommentaryMapper.getLongCommentaryList();
    }

    @Override
    public boolean collection(int id, int userId) {
        Collections collection = new Collections();
        collection.setCommentaryId(id);
        collection.setUserId(userId);
        int ops = collectionsMapper.insert(collection);
        return ops>0?true:false;
    }

    @Override
    public boolean cancelCollection(int id, int userId) {
        Collections collection = new Collections();
        collection.setCommentaryId(id);
        collection.setUserId(userId);
        int ops = collectionsMapper.delete(collection);
        return ops>0?true:false;
    }

    @Override
    public boolean searchLike(int userId, String commentaryId) {
        String dir = "like:";
        boolean exist = redisTemplate.opsForSet().isMember(dir+commentaryId,userId);
        return exist;
    }

    @Override
    public boolean searchCollections(int userId, int commentaryId) {
        int count = collectionsMapper.exist(userId,commentaryId);
        if (count>0)
            return true;
        return false;
    }

    @Override
    public List<LongCommentaryDto> getCollection(int userId) {
        return longCommentaryMapper.getCollectionCommentaryList(userId);
    }

    @Override
    public LongCommentaryDto get(int id) {
        return longCommentaryMapper.get(id);
    }

    @Override
    public List<LongCommentaryDto> search(LongCommentarySearchDto searchDto) {
        return longCommentaryMapper.search(searchDto);
    }

    @Override
    public boolean delete(int id) {
        if (longCommentaryMapper.deleteSoft(id) > 0)
            return true;
        return false;
    }

    @Override
    public long likeNumber(int id) {
        String dir = "like:";
        return redisTemplate.opsForSet().size(dir+id);
    }
}
