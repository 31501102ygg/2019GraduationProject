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
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
                .addHeader("Cookie", "gr_user_id=b6c0778d-f8df-4963-b057-bd321593de1e; bid=T-M5aFmoLY0; __yadk_uid=WvMJfSHd1cjUFrFQTdN9KnkIOkR2AFZu; viewed=\"26311273_26877306_26340992_26649178_3199438_3015786_27038473_10793398_26754665\"; ll=\"108296\"; ps=y; dbcl2=\"141556470:E4oz3is9RMY\"; ap=1; _vwo_uuid_v2=E57494AA9988242B62FB576F22211CE4|e95afc3b3a6c74f0b9d9106c6546e73e; ck=OvCX; __utma=30149280.1283677058.1481968276.1531194536.1531389580.35; __utmc=30149280; __utmz=30149280.1524482884.31.29.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=30149280.14155; __utma=223695111.1691619874.1522208966.1531194536.1531389615.5; __utmc=223695111; __utmz=223695111.1524483025.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1531389615%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3D0saOVVzXJiEvkbYGxCXZ849EweAjA2om6cIvPZ7FxE35FrmKU8CfOHm1cC9Xs0JS%26wd%3D%26eqid%3De5307bbf0006c241000000045addc33f%22%5D; _pk_id.100001.4cf6=cee42334e421195b.1522208966.5.1531389615.1531200476.; push_noty_num=0; push_doumail_num=0")
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
