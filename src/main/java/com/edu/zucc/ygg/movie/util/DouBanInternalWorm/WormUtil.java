package com.edu.zucc.ygg.movie.util.DouBanInternalWorm;

import com.edu.zucc.ygg.movie.domain.Movie;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class WormUtil {
    private UrlManager manager;
    private Downloader downloader;
    private PageParser parser;
    private DataProcessor processor;
    private List<String> filmUrlList = new ArrayList<>();

    public WormUtil(UrlManager manager,
                   Downloader downloader,
                   PageParser parser,
                   DataProcessor processor) {
        this.manager = manager;
        this.downloader = downloader;
        this.parser = parser;
        this.processor = processor;
    }

    public static void main(String[] args) {

        // 豆瓣影评URL部分不变，变化的只有参数部分
        final String BASE_URL = "https://movie.douban.com/j/new_search_subjects";
        final String ROOT_URL = BASE_URL + "?sort=U&range=0,10&tags=&start=20";

        // 构建爬虫并启动爬虫，这里仅作最小化演示，程序健壮性、扩展性等暂不考虑
        WormUtil crawler = new WormUtil(new UrlManager(BASE_URL, ROOT_URL),
                new Downloader(),
                new PageParser(),
                new DataProcessor("192.168.0.105"));
        long urls = crawler.getFilmUrls();
        urls = crawler.start();
        System.out.println("任务执行完成，共爬取"+urls+"个URL:");
    }

    /**
     * 启动爬虫，任务执行完成后，返回处理URL数量
     *
     * @return
     */
    private long getFilmUrls() {
        final AtomicLong counter = new AtomicLong();
            try {
                String url = manager.getNewUrl();
                counter.incrementAndGet();
                String html = downloader.download(url);
                JSONObject jsonObject = JSONObject.fromObject(html);
                JSONArray jsonArray = (JSONArray) jsonObject.get("data");
                for(Object object:jsonArray){
                    JSONObject jsonObject1 = (JSONObject) object;
                    String urlFilm = (String)jsonObject1.get("url");
                    System.out.println(urlFilm);
                    filmUrlList.add(urlFilm);
                }
                manager.appendNewUrls(filmUrlList);
            } catch (Exception e) {
                System.out.println(e);
            }
        return counter.get();
    }

    private long start(){
        final AtomicLong counter = new AtomicLong();
        while(manager.hasNewUrl()){
            try{
                Thread.sleep(10000);
                counter.incrementAndGet();
                String url = manager.getNewUrl();
                String html = downloader.download(url);
                Movie movie = parser.parse(html);
                System.out.println(movie.getName()+"    "+movie.getImgUrl());
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return counter.get();
    }
}
