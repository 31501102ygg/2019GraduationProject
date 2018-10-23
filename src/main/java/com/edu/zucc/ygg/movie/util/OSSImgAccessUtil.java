package com.edu.zucc.ygg.movie.util;

import com.aliyun.oss.OSSClient;
import com.edu.zucc.ygg.movie.domain.STSAccount;
import com.edu.zucc.ygg.movie.service.impl.StsServiceImpl;

import java.net.URL;
import java.util.Date;

public class OSSImgAccessUtil {
    private OSSClient ossClient;
    String bucketName = "ygg-31501102-bucket";
    // accessKey
    private String accessKeyId = "LTAItiUlwVCLXbi9";
    private String accessKeySecret = "EkYdAZjYzBewNF5V5lXVcn1s0fnpsh";

    // 创建OSSClient实例。
    public void initOSSClient(){
        STSAccount stsAccount = StsServiceImpl.stsAccount;
        if (stsAccount.getAccessKeyId()==null){
            StsServiceImpl.getSTSAccount(accessKeyId,accessKeySecret);
        }
        this.ossClient = new OSSClient(stsAccount.getEndpoint(), stsAccount.getAccessKeyId(), stsAccount.getSecurityToken());
    }
    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    public String getImgAccessUrl(String imgUrl) {
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, imgUrl, expiration);
        return url.toString();
    }

    public static void main(String [] args){
        Date date = new Date(1539847617);
        System.out.println(date);
        Date date1 = new Date();
        System.out.println(date1);
    }
}
