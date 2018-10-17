package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.dao.TestDomainMapper;
import com.edu.zucc.ygg.movie.domain.TestDomain;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiresAuthentication
public class TestController {
    @Autowired
    TestDomainMapper testDomainMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation(value = "搭建测试")
    @RequiresAuthentication
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    public String HelloSpring (){
        return "hello spring boot";
    }

    @RequestMapping(value = "/mybatisTest", method = RequestMethod.GET)
    @ApiOperation(value = "mybatis测试")
    @ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto mybatisTest (){
            TestDomain testDomain = testDomainMapper.selectByPrimaryKey(1);
            return ResultDtoFactory.toAck("mybatis测试",testDomain.getName());
    }


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto processMethod(AuthorizationException ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
        return ResultDtoFactory.toNack("权限不够");
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDto processMethod(UnauthenticatedException ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
        return ResultDtoFactory.toNack("Token没有授权");
    }
//    RequiresAuthentication:使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。
//    RequiresGuest:使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份，不需要经过认证或者在原先的session中存在记录。
//    RequiresPermissions:当前Subject需要拥有某些特定的权限时，才能执行被该注解标注的方法。如果当前Subject不具有这样的权限，则方法不会被执行。
//    RequiresRoles:当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。如果当天Subject不同时拥有所有指定角色，则方法不会执行还会抛出AuthorizationException异常。
//    RequiresUser:当前Subject必须是应用的用户，才能访问或调用被该注解标注的类，实例，方法。

}
