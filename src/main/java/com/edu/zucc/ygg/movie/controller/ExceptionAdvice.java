package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yl1794 on 2018/6/14.
 */
@ControllerAdvice
public class ExceptionAdvice{

    /**
     * 全局捕获AuthorizationException异常，并进行相应处理
     */
    @ExceptionHandler({AuthorizationException.class})
    @ResponseBody
    public ResultDto handleException(Exception e){
        return ResultDtoFactory.toNack("没有权限");
    }


//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultDto processMethod(UnauthenticatedException ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
//        return ResultDtoFactory.toNack("Token没有授权");
//    }
//    RequiresAuthentication:使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。
//    RequiresGuest:使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份，不需要经过认证或者在原先的session中存在记录。
//    RequiresPermissions:当前Subject需要拥有某些特定的权限时，才能执行被该注解标注的方法。如果当前Subject不具有这样的权限，则方法不会被执行。
//    RequiresRoles:当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。如果当天Subject不同时拥有所有指定角色，则方法不会执行还会抛出AuthorizationException异常。
//    RequiresUser:当前Subject必须是应用的用户，才能访问或调用被该注解标注的类，实例，方法。


}
