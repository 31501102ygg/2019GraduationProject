package com.edu.zucc.ygg.movie.schedule;

import com.edu.zucc.ygg.movie.service.LongCommentaryService;
import com.edu.zucc.ygg.movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ReadSchedule {
    private static Logger logger = LoggerFactory.getLogger(ReadSchedule.class);
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MovieService movieService;
    @Autowired
    LongCommentaryService longCommentaryService;

    //每天晚上12点对redis数据库阅读量数据进行读取更新到数据库
    @Scheduled(cron = "0 0 0 * * *")
    public void updateReadInDB(){
        String key = "movie:watch";
        Set<Integer> ready = redisTemplate.opsForZSet().reverseRange(key,0,Long.MAX_VALUE);
        for(int movieId:ready){
            int count = redisTemplate.opsForZSet().score(key,movieId).intValue();
            movieService.moviePageReadNumUpdate(movieId,count);
            logger.info("更新"+movieId+"阅读数: "+count);
        }
    }

    @Scheduled(cron = "0 0 0/12 * * *")
    public void updateMovieScore(){
        List<Map<String,Object>> movieScores = longCommentaryService.getAvgScore();
        movieScores.forEach(oneScore->{
            int id = (int)oneScore.get("id");
            double score = Double.parseDouble(oneScore.get("score").toString());
            movieService.updateMovieScore(id,score);
        });
    }

    //检查redis中阅读数据是否消失，消失从数据库中恢复
    @Scheduled(cron = "0 0/10 * * * *")
    public void checkRedisDataIsEmpty() {
        String key = "movie:watch";
        long size = redisTemplate.opsForZSet().size(key);
        if (size>0)
            return;
        logger.info("redis阅读数据恢复");
        List<Map<String,Integer>> reads = movieService.getMovieReadNum();
        reads.forEach(read->{
            int movieId = read.get("movie_id");
            double count = read.get("count");
            redisTemplate.opsForZSet().add(key,movieId,count);
        });
    }
}
