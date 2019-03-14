package com.edu.zucc.ygg.movie.controller;

import com.edu.zucc.ygg.movie.constant.ApplicationConstant;
import com.edu.zucc.ygg.movie.domain.UpgradePro;
import com.edu.zucc.ygg.movie.dto.LongCommentaryDto;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.dto.UpgradeProDto;
import com.edu.zucc.ygg.movie.dto.UpgradeSearchDto;
import com.edu.zucc.ygg.movie.service.UpgradeProService;
import com.edu.zucc.ygg.movie.service.UserService;
import com.edu.zucc.ygg.movie.util.JWTUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/upgrade")
public class UpgradeProController {
    @Autowired
    UserService userService;
    @Autowired
    UpgradeProService upgradeProService;

    @PostMapping("add")
    @ApiOperation(value = "申请专家接口")@ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("user")
    public ResultDto addUpgrade(HttpServletRequest request, @RequestParam String content){
        if (StringUtil.isEmpty(content))
            return ResultDtoFactory.toNack("申请信息不能为空");
        String token = request.getHeader("Authorization");
        if (StringUtil.isEmpty(token))
            return ResultDtoFactory.toNack("你没有登录，无法获取token");
        String tokenUserName = JWTUtil.getUsername(token);
        if (StringUtil.isEmpty(tokenUserName))
            return ResultDtoFactory.toNack("无法获取用户名，无效的token");
        Integer userId = userService.getUserId(tokenUserName);
        if (userId==null)
            return ResultDtoFactory.toNack("无法获取用户ID，无效的token");
        if(upgradeProService.exist(userId))
            return ResultDtoFactory.toNack("您当前还有没有审核的申请，不要重复操作");
        UpgradePro upgradePro = new UpgradePro();
        upgradePro.setContent(content);
        upgradePro.setUsername(tokenUserName);
        upgradePro.setUserId(userId);
        upgradePro.setState(0);
        int flag = upgradeProService.add(upgradePro);
        if (flag>0){
            return ResultDtoFactory.toAck("你的申请已经提交，还需要后台确认");
        }
        return ResultDtoFactory.toNack("数据库错误，申请失败");
    }

    @GetMapping("pass")
    @ApiOperation(value = "专家申请审核通过接口")@ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto passUpgrade(@RequestParam int id){
        int userId = upgradeProService.getUserId(id);
        userService.upgradeToPro(userId);
        int flag = upgradeProService.update(id,1);
        if (flag>0){
            return ResultDtoFactory.toAck("操作完成");
        }
        return ResultDtoFactory.toNack("操作失败");
    }

    @GetMapping("unpass")
    @ApiOperation(value = "专家申请审核通过接口")@ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto unPassUpgrade(@RequestParam int id){
        int flag = upgradeProService.update(id,2);
        if (flag>0){
            return ResultDtoFactory.toAck("操作完成");
        }
        return ResultDtoFactory.toNack("操作失败");
    }

    @PostMapping("search")
    @ApiOperation(value = "专家申请审核信息查询接口")@ApiImplicitParams({@ApiImplicitParam(name = ApplicationConstant.AUTHORIZATION, required = true, paramType = ApplicationConstant.HTTP_HEADER)})
    @RequiresRoles("admin")
    public ResultDto searchUpgrade(@RequestBody UpgradeSearchDto searchDto){
        int pageNum = searchDto.getPageNum()<=0?1:searchDto.getPageNum();
        int pageSize = searchDto.getPageSize()<=0?10:searchDto.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UpgradePro> pageInfo = new PageInfo<>(upgradeProService.search(searchDto));
        List<UpgradePro> lists = pageInfo.getList();
        lists.forEach(list ->{list.transformDateToString();});
        Map<String,Object> map = new HashMap<>();
        map.put("list",lists);
        map.put("total",pageInfo.getTotal());
        return ResultDtoFactory.toAck("查询成功",map);
    }
}
