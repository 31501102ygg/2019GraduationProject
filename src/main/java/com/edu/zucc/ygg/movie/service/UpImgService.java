package com.edu.zucc.ygg.movie.service;

import com.edu.zucc.ygg.movie.exception.ImgException;
import org.springframework.web.multipart.MultipartFile;


public interface UpImgService {

    String updateHead(MultipartFile file)throws ImgException;
}


