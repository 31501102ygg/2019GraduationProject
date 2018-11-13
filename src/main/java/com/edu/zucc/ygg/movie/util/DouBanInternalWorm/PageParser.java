package com.edu.zucc.ygg.movie.util.DouBanInternalWorm;

import com.edu.zucc.ygg.movie.domain.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageParser<T> {

    public Movie parse(String html) {
        Movie movie = new Movie();
        Document doc = Jsoup.parse(html);
        String name = doc.selectFirst("h1>span").text();
        String imgUrl = doc.selectFirst("div#mainpic >a >img").attr("src");
        Elements info = doc.select("div#info >span");
        Elements p1List = doc.select("div#info > span.p1");
        movie.setName(name);
        movie.setImgUrl(imgUrl);
//        // 获取链接列表
//        List<String> links = doc.select(".list-wp > a[href]").stream()
//                .map(a -> a.attr("abs:href"))
//                .collect(Collectors.toList());

//        // 获取数据列表
//        List<Map<String, String>> results = doc.select("#comments > div.comment-item")
//                .stream()
//                .map(div -> {
//                    Map<String, String> data = new HashMap<>();
//
//                    String author = div.selectFirst("h3 > span.comment-info > a").text();
//                    String date = div.selectFirst("h3 > span.comment-info > span.comment-time").text();
//                    Element rating = div.selectFirst("h3 > span.comment-info > span.rating");
//                    String star = null;
//                    if (rating != null) {
//                        // allstar40 rating
//                        star = rating.attr("class");
//                        star = star.substring(7, 9);
//                    }
//                    String vote = div.selectFirst("h3 > span.comment-vote > span.votes").text();
//                    String comment = div.selectFirst("div.comment > p").text();
//
//                    data.put("author", author);
//                    data.put("date", date);
//                    if (star != null)
//                        data.put("star", star);
//                    data.put("vote", vote);
//                    data.put("comment", comment);
//
//                    return data;
//                })
//                .collect(Collectors.toList());

        return movie;
    }

}
