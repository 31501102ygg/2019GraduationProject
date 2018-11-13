package com.edu.zucc.ygg.movie.util.DouBanInternalWorm;

import java.util.List;

public class DataProcessor<T> {

    private static final int DEFAULT_PORT = 27017;

    public DataProcessor(String host) {
        this(host, DEFAULT_PORT);
    }

    public DataProcessor(String host, int port) {
        // TODO 配置Mongo连接
    }

    public void process(List<T> results) {
        if (results == null || results.isEmpty()) {
            return;
        }

        // 暂不写入MongoDB，打印出结果即可
        // {date=2018-07-04, star=50, author=忻钰坤, comment=“你敢保证你一辈子不得病？”纯粹、直接、有力！常常感叹：电影只能是电影。但每看到这样的佳作，又感慨：电影不只是电影！由衷的希望这部电影大卖！成为话题！成为榜样！成为国产电影最该有的可能。, vote=27694}
        // {date=2018-07-03, star=50, author=沐子荒, comment=王传君所有不被外人理解的坚持，都在这一刻得到了完美释放。他不是关谷神奇，他是王传君。 你看，即使依旧烂片如云，只要还有哪怕极少的人坚持，中国影视也终于还是从中生出了茁壮的根。 我不是药神，治不好这世界。但能改变一点，总归是会好的。, vote=26818}
        // {date=2018-06-30, star=50, author=凌睿, comment=别说这是“中国版《达拉斯买家俱乐部》”了，这是中国的真实事件改编的中国电影，是属于我们自己的电影。不知道就去百度一下“陆勇”，他卖印度抗癌药的时候《达拉斯买家俱乐部》还没上映呢。所以别提《达拉斯买家俱乐部》了，只会显得你无知。（别私信我了，我800年前就知道《达拉斯》也是真事改编）, vote=18037}
        // ... ...
        results.stream()
                .forEach(data -> {
                    System.out.println(data);
                });


    }

}
