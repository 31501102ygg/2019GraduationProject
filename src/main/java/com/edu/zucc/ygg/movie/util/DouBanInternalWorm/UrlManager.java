package com.edu.zucc.ygg.movie.util.DouBanInternalWorm;

import java.util.*;
import java.util.stream.Collectors;

public class UrlManager {

    private String baseUrl;
    private Queue<String> newUrls = new LinkedList<>();
    private Set<String> oldUrls = new HashSet<>();

    public UrlManager(String baseUrl, String rootUrl) {
        this(baseUrl, Arrays.asList(rootUrl));
    }


    public UrlManager(String baseUrl, List<String> rootUrls) {
        if (baseUrl == null || rootUrls == null || rootUrls.isEmpty()) {
            return;
        }
        this.baseUrl = baseUrl;
        // 添加待抓取URL列表
        this.appendNewUrls(rootUrls);

    }

    /**
     * 追加待抓取URLs
     *
     * @param urls
     */
    public void appendNewUrls(List<String> urls) {
        // 添加待抓取URL列表
        newUrls.addAll(urls);
    }

    public boolean hasNewUrl() {
        return !this.newUrls.isEmpty();
    }

    /**
     * 取出一个新URL，这里简化处理了新旧URL状态迁移过程，取出后即认为成功处理了(实际情况下需要考虑各种失败情况和边界条件)
     *
     * @return
     */
    public String getNewUrl() {
        String url = this.newUrls.poll();
        this.oldUrls.add(url);
        return url;
    }
}