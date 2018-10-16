package com.edu.zucc.ygg.movie.util;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface MyMapper<T> extends Mapper<T> {
    // 注解 @TargetDataSource 不可以在这里使用
//    List<T> likeName(String name);

//    T getById(int id);

//    String getNameById(int id);
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
