package com.edu.zucc.ygg.movie.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.edu.zucc.ygg.movie.dto.ResultDto;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.util.OSSImgAccessUtil;
import com.edu.zucc.ygg.movie.util.ResultDtoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 图片上传
 * @author Monkey
 * @date 2017年9月30日下午3:37:00
 * @version 1.0
 */
@Controller
public class UpImgController {
    public static final Logger logger = LoggerFactory.getLogger(UpImgController.class);
    @Autowired
    private UpImgService upImgService;

    @RequestMapping(value = "/headImgUpload", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String headImgUpload(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> value = new HashMap<String, Object>();
        try {
            String url = upImgService.updateHead(file);
            logger.debug("图片路径{}",url);
            value.put("data", url);
            value.put("code", 0);
            value.put("msg", "图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            value.put("code", 2000);
            value.put("msg", "图片上传失败");
        }
        return JSONObject.toJSONString(value);
    }

    @RequestMapping(value = "/accessImgUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto accessImg(@RequestParam String imgAddress) {
        OSSImgAccessUtil ossImgAccessUtil = new OSSImgAccessUtil();
        ossImgAccessUtil.initOSSClient();
        String imgAccessUrl = ossImgAccessUtil.getImgAccessUrl(imgAddress);
        return ResultDtoFactory.toAck("图片访问地址创建成功",imgAccessUrl);
    }
}
