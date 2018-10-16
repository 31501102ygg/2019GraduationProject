package com.edu.zucc.ygg.movie.service;

import java.util.List;

public interface Service<T> {


    //
    // insert
    // ----------------------------------------------------------------------------------------------------

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
    T insert(T record);


    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    T insertSelective(T record);



    //
    // update
    // ----------------------------------------------------------------------------------------------------

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    T update(T record);


    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    T updateSelective(T record);


    //
    // delete
    // ----------------------------------------------------------------------------------------------------

    /**
     * 根据主键删除
     *
     * @param id id不能为空
     * @return
     */
    int delete(Long id);




    /**
     * 根据实体属性作为条件进行删除
     *
     * @param record
     * @return
     */
    int delete(T record);


    //
    // select
    // ----------------------------------------------------------------------------------------------------

    /**
     * 根据主键查询
     *
     * @param id 不能为空
     * @return
     */
    T get(Long id);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常
     *
     * @param record
     * @return
     */
    T get(T record);

    /**
     * 根据字段和值查询 返回一个
     *
     * @param key   不能为空
     * @param value 不能为空
     * @return
     */
    T get(String key, Object value);




    /**
     * 根据实体中的属性值进行查询
     *
     * @param record
     * @return
     */
    List<T> select(T record);

    /**
     * 根据属性和值查询
     *
     * @param key
     * @param value
     * @return
     */
    List<T> select(String key, Object value);

    /**
     * 根据实体中的属性值进行分页查询
     *
     * @param record
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<T> select(T record, int pageNum, int pageSize);

    /**
     * 查询全部结果
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性查询总数
     *
     * @param record
     * @return
     */
    int count(T record);


}
