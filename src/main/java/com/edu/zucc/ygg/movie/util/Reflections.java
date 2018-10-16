package com.edu.zucc.ygg.movie.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class Reflections {

    private static Logger logger = LoggerFactory.getLogger(Reflections.class);

    /**
     * 通过反射, 获得Class定义中声明的泛型参数的类型, 注意泛型必须定义在父类处. 如无法找到, 返回Object.class.
     *
     * @param clazz class类
     *
     * @return the 返回第一个声明的泛型类型. 如果没有,则返回Object.class
     */
    @SuppressWarnings("unchecked")
    public static Class getClassGenericType(final Class clazz) {
        return getClassGenericType(clazz, 0);
    }

    /**
     * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     *
     * @param clazz class类
     *
     * @param index 获取第几个泛型参数的类型,默认从0开始,即第一个
     *
     * @return 返回第index个泛型参数类型.
     */
    public static Class getClassGenericType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }

        return (Class) params[index];
    }

    /**
     * 根据注解类型获取实体的Field
     *
     * @param entityClass 实体类型
     *
     * @param annotationClass 注解类型
     *
     * @return 返回第一个有该注解类型的Field,如果没有则返回null.
     */
    @SuppressWarnings("unchecked")
    public static Field getFieldByAnnotation(Class entityClass, Class annotationClass) {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(annotationClass) != null) {
                makeAccessible(field);
                return field;
            }
        }
        return null;
    }

    /**
     * 获取实体的字段
     *
     * @param entityClass 实体类型
     *
     * @param fieldName 字段名称
     *
     * @return 该字段名称对应的字段,如果没有则返回null.
     */
    public static Field getField(Class entityClass, String fieldName){
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            makeAccessible(field);
            return field;
        } catch (NoSuchFieldException e) {
            logger.error("NoSuchFieldException",e);
        }
        return null;
    }


    /**
     * 改变private/protected的成员变量为public.
     */
    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }
}
