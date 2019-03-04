package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.dao.LongCommentaryMapper;
import com.edu.zucc.ygg.movie.domain.LongCommentary;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;
import com.edu.zucc.ygg.movie.service.LongCommentaryService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LongCommentaryServiceImpl extends BaseService<LongCommentary> implements LongCommentaryService {
    @Autowired
    LongCommentaryMapper longCommentaryMapper;

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
}
