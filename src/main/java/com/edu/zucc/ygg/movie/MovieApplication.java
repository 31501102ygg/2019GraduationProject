package com.edu.zucc.ygg.movie;

import com.edu.zucc.ygg.movie.util.MyMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement  //启用事务管理
@SpringBootApplication(scanBasePackages = "com.edu.zucc.ygg.movie")
@MapperScan(basePackages="com.edu.zucc.ygg.movie.dao",markerInterface = MyMapper.class)
public class MovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
}
