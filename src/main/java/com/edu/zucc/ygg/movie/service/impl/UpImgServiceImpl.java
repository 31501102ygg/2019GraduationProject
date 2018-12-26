package com.edu.zucc.ygg.movie.service.impl;

import com.edu.zucc.ygg.movie.exception.ImgException;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.util.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

@Service
public class UpImgServiceImpl implements UpImgService {
    public static final Logger logger = LoggerFactory.getLogger(UpImgServiceImpl.class);
    @Override
    public String updateHead(MultipartFile file) throws ImgException {
        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    @Override
    public String updateUserHeaderImg(MultipartFile file) throws ImgException{
        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImgHeader(file);
        String imgUrl = ossClient.getHearImgUrl(name,"movie_headerImg/");
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    @Override
    public String updateHead(String fileUrl) throws ImgException {
        if (StringUtil.isEmpty(fileUrl)) {
            throw new ImgException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(fileUrl);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    @Override
    public String updateSlideImg(MultipartFile file) throws ImgException {
        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg(file,"movie_slide/");
        String imgUrl = ossClient.getHearImgUrl(name,"movie_slide/");
        String[] split = imgUrl.split("\\?");
        return split[0];
    }
}
