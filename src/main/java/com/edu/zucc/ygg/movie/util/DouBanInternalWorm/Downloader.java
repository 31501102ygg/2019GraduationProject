package com.edu.zucc.ygg.movie.util.DouBanInternalWorm;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Downloader {

    private OkHttpClient client = new OkHttpClient();

    /**
     * 下载网页
     *
     * @param url
     * @return
     */
    public String download(String url) {
        // 使用Cookie消息头是为了简化登录问题(豆瓣电影评论不登录条件下获取不到全部数据)
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36")
                .addHeader("Cookie", "bid=gUzfoT3pFVI; ll=\"118172\"; __yadk_uid=fQIe7MhrdRh9CJFDb5M6ebEUL06Q6lvI; _vwo_uuid_v2=D100B43F9018074B470633FD2C28D74F4|69bbead2672894630dc44dc66a46c26f; ps=y; push_noty_num=0; push_doumail_num=0; douban-profile-remind=1; __utmv=30149280.17956; gr_user_id=f5409d09-4317-43ad-bc38-44ef82cce793; __utmc=30149280; __utmc=223695111; ap_v=0,6.0; dbcl2=\"179567148:jApgP53Mbtk\"; ck=Z9Uy; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1542181343%2C%22https%3A%2F%2Fwww.douban.com%2Faccounts%2Flogin%3Fredir%3Dhttps%253A%252F%252Fmovie.douban.com%252F%22%5D; _pk_ses.100001.4cf6=*; __utma=30149280.1905281004.1539835562.1542176559.1542181343.23; __utmb=30149280.0.10.1542181343; __utmz=30149280.1542181343.23.12.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/accounts/login; __utma=223695111.1421376414.1539835564.1542176559.1542181343.24; __utmb=223695111.0.10.1542181343; __utmz=223695111.1542181343.24.13.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/accounts/login; _pk_id.100001.4cf6=4faa41e1fae00ed1.1539835564.22.1542181874.1542178846.")
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException(response.code() + "," + response.message());
            }
            return response.body().string();
        } catch (IOException e) {
            System.out.println("下载网页"+url+"失败!"+e);
        }
        return null;
    }
}
