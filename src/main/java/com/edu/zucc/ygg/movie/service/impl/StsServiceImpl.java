package com.edu.zucc.ygg.movie.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.edu.zucc.ygg.movie.domain.STSAccount;

import java.util.Map;

public class StsServiceImpl {
    public static STSAccount stsAccount = new STSAccount();

    public static void getSTSAccount(String accessKeyId,String accessKeySecret) {
        String endpoint = "sts.cn-shenzhen.aliyuncs.com";
        String roleArn = "acs:ram::1456930348047384:role/ygg-movie-2018";
        String roleSessionName = "ygg-01";
        String policy = null;
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // Optional
            final AssumeRoleResponse response = client.getAcsResponse(request);
            stsAccount.setEndpoint("oss-cn-shenzhen.aliyuncs.com");
            stsAccount.setAccessKeyId(response.getCredentials().getAccessKeyId());
            stsAccount.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
            stsAccount.setSecurityToken(response.getCredentials().getSecurityToken());
        } catch (ClientException e) {
            System.out.println("Failed：");
            System.out.println("Error code: " + e.getErrCode());
            System.out.println("Error message: " + e.getErrMsg());
            System.out.println("RequestId: " + e.getRequestId());
        }
    }
}