package com.edu.zucc.ygg.movie.controller;


import com.edu.zucc.ygg.movie.domain.SRegion;
import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.service.SRegionService;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/SRegion")
@Api(value = "省市区接口")
public class SRegionController {
    @Autowired
    SRegionService sRegionService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultDto list(@RequestParam Integer parentID, @RequestParam Integer level) {
        SRegion sRegion = new SRegion();
        sRegion.setParentId(parentID);
        sRegion.setLevelType(level);
        return ResultDtoFactory.toAck("success", sRegionService.select(sRegion));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultDto getByID(@PathVariable Integer id) {
        return ResultDtoFactory.toAck("success", sRegionService.getByID(id));

    }
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @Cacheable(value = "SRegionCache", key = "'SRegionAll'")
    public ResultDto SRegion() {
        List<SRegion> ls = sRegionService.list(0);
        List<SRegion> ls2 = test(ls);
        return ResultDtoFactory.toAck("success", ls2);
    }
    public List<SRegion> test(List<SRegion> ls) {
        Iterator<SRegion> it = ls.iterator();
        while (it.hasNext()) {
            SRegion sRegion = it.next();
            List<SRegion> ls2 = sRegionService.list(sRegion.getId());
            if (ls2.size() == 0) {
                continue;
            } else {
                sRegion.setChildren(ls2);
                test(ls2);
            }
        }
        return ls;
    }

//    @GetMapping("/redis")
//    public String redis(){
//        //字符串
//        stringRedisTemplate.opsForValue().set("rediskey","redisvalue");
//        String rediskey = stringRedisTemplate.opsForValue().get("rediskey");
//        System.out.println(rediskey);
//        //对象
//        User user = new User(1,"username");
//        redisCacheTemplate.opsForValue().set("user",user);
//
//        User getUser = (User) redisCacheTemplate.opsForValue().get("user");
//        System.out.println(getUser.getUserName());
//        return "redis";
//    }
}
