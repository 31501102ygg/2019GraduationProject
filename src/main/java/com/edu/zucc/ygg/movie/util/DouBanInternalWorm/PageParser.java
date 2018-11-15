package com.edu.zucc.ygg.movie.util.DouBanInternalWorm;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tk.mybatis.mapper.util.StringUtil;

import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PageParser<T> {

    public Movie parse(String html) throws ParseException {
        Movie movie = new Movie();
        Document doc = Jsoup.parse(html);
        String name = doc.selectFirst("title").text();
        name = name.split("\\(")[0];
        String name2 = doc.getElementsByAttributeValue("property", "v:itemreviewed").text();
        if (name2!=null) {
            Pattern patternFName = Pattern.compile("[a-z|A-Z][a-z|A-Z|\\s|\\:|\\-|\\·|\\.|\\'|0-9]+");
            Matcher matcherFName = patternFName.matcher(name2);
            if (matcherFName.find()){
                movie.setForeignName(matcherFName.group().trim());
            }
        }
        String introduction = null;
        String imgUrl = doc.selectFirst("div#mainpic >a >img").attr("src");
        if (doc.selectFirst("span.hidden")==null) {
            introduction = doc.selectFirst("div#link-report >span").text().trim();
        }else{
            introduction = doc.selectFirst("span.hidden").text().trim();
        }
        Elements infos = doc.select("div#info").get(0).children();
        for (Element info : infos) {
            if (info.childNodeSize() > 0) {
                String key = info.getElementsByAttributeValue("class", "pl").text();
                if ("导演".equals(key)) {
                    movie.setDirector(info.getElementsByAttributeValue("class", "attrs").text());
                } else if ("编剧".equals(key)) {
                    movie.setScreenwriter(info.getElementsByAttributeValue("class", "attrs").text());
                } else if ("主演".equals(key)) {
                    movie.setActors(info.getElementsByAttributeValue("class", "attrs").text());
                } else if ("类型:".equals(key)) {
                    movie.setType(doc.getElementsByAttributeValue("property", "v:genre").text());
                } else if ("制片国家/地区:".equals(key)) {
                    Pattern patternCountry = Pattern.compile(".制片国家/地区:</span>+\\s.+[\\u4e00-\\u9fa5\\s*\\/*]+.+\\s+<br>");
                    Matcher matcherCountry = patternCountry.matcher(doc.html());
                    if (matcherCountry.find()) {
                        movie.setMakeState(matcherCountry.group().split("</span>")[1].split("<br>")[0].trim());// for example: >制片国家/地区:</span> 中国大陆 / 香港     <br>
                    }
                } else if ("语言:".equals(key)) {
                    Pattern patternLanguage = Pattern.compile(".语言:</span>.+\\s.+[\\u4e00-\\u9fa5\\s*\\/*]+.+\\s+<br>");
                    Matcher matcherLanguage = patternLanguage.matcher(doc.html());
                    if (matcherLanguage.find()) {
                        movie.setLanguage(matcherLanguage.group().split("</span>")[1].split("<br>")[0].trim());
                    }
                } else if ("上映日期:".equals(key)) {
                    String date = doc.getElementsByAttributeValue("property", "v:initialReleaseDate").text();
                    Pattern patternReleaseDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                    Matcher matcherReleaseDate = patternReleaseDate.matcher(date);
                    if (matcherReleaseDate.find()){
                        movie.setReleaseTime(DateUtil.convertToDate(matcherReleaseDate.group(0)));
                    }
                } else if ("片长:".equals(key)) {
                    movie.setSheetLength(Integer.parseInt(doc.getElementsByAttributeValue("property", "v:runtime").attr("content")));
                } else if ("又名:".equals(key)){
                    if (StringUtil.isEmpty(movie.getForeignName())) {
                        Pattern patternFName = Pattern.compile(".又名:</span>+\\s.+[\\u4e00-\\u9fa5\\s*\\/*]+.+\\s+<br>");
                        Matcher matcherFName = patternFName.matcher(doc.html());
                        String foreignName = null;
                        if (matcherFName.find()) {
                            foreignName = matcherFName.group().split("</span>")[1].split("<br>")[0].trim();
                        }
                        if (foreignName != null) {
                            patternFName = Pattern.compile("[a-z|A-Z][a-z|A-Z|\\s|\\:|\\-|\\·|\\.|\\'|0-9]+");
                            matcherFName = patternFName.matcher(foreignName);
                            if (matcherFName.find()) {
                                movie.setForeignName(matcherFName.group().trim());
                            }
                        }
                    }
                }
            }
        }
        movie.setName(name);
        movie.setImgUrl(imgUrl);
        movie.setIntroduction(introduction);
        movieClearBlank(movie);
        return movie;
    }

    private void movieClearBlank(Movie movie){
        movie.setDirector(splitString(movie.getDirector()));
        movie.setScreenwriter(splitString(movie.getScreenwriter()));
        movie.setActors(splitString(movie.getActors()));
        movie.setType(movie.getType().replace(" ","/"));
        movie.setLanguage(splitString(movie.getLanguage()));
        movie.setMakeState(splitString(movie.getMakeState()));
    }

    private String splitString(String s){
        List<String> strs = new ArrayList<>();
        String newString = "";
        if (s.indexOf("/")>0){
            strs.addAll(Arrays.asList(s.split("/")));
        }
        for (String a : strs){
            newString += a.trim()+"/";
        }
        if (!StringUtil.isEmpty(newString))
            newString = newString.substring(0,newString.length()-1);
        else
            newString += s.trim();
        return newString;
    }
}
