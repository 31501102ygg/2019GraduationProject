package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.service.Service;
import com.edu.zucc.ygg.movie.util.MyMapper;
import com.edu.zucc.ygg.movie.util.Reflections;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

public abstract class BaseService<T> implements Service<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private MyMapper<T> mapper;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        this.entityClass = Reflections.getClassGenericType(getClass());
    }

    //
    // insert
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public T insert(T record) {
        mapper.insert(record);
        return record;
    }


    @Transactional(rollbackFor = Exception.class)
    public T insertSelective(T record) {
        mapper.insertSelective(record);
        return record;
    }


    //
    // update
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public T update(T record) {
        mapper.updateByPrimaryKey(record);
        return record;
    }


    @Transactional(rollbackFor = Exception.class)
    public T updateSelective(T record) {
        mapper.updateByPrimaryKeySelective(record);
        return record;
    }


    //
    // delete
    // ----------------------------------------------------------------------------------------------------
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }



    @Transactional(rollbackFor = Exception.class)
    public int delete(T record) {
        return mapper.delete(record);
    }



    //
    // select
    // ----------------------------------------------------------------------------------------------------
    public T get(Long id) {
        T entity = null;
        try {
            entity = entityClass.newInstance();
            Field idField = Reflections.getFieldByAnnotation(entityClass, Id.class);
            idField.set(entity, id);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return mapper.selectByPrimaryKey(entity);
    }

    public T get(T record) {
        return mapper.selectOne(record);
    }

    public T get(String key, Object value) {
        T entity = null;
        try {
            entity = entityClass.newInstance();
            Field field = Reflections.getField(entityClass, key);
            field.set(entity, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }

        return mapper.selectOne(entity);
    }


    public List<T> select(T record) {

        return mapper.select(record);
    }

    public List<T> select(String key, Object value) {
        T entity = null;
        try {
            entity = entityClass.newInstance();
            Field field = Reflections.getField(entityClass, key);
            field.set(entity, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }
        return mapper.select(entity);
    }

    public List<T> select(T record, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return mapper.select(record);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public int count(T record) {
        return mapper.selectCount(record);
    }

}
