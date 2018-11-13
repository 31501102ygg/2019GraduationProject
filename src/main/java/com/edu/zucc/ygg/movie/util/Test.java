package com.edu.zucc.ygg.movie.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Test {

    public static void main(String args[]){
        String url = "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2233971046.jpg";
        String path = "C:\\Users\\de'l'l\\Desktop\\testImg";
        getHtmlPicture(url,path,null);
    }

    public static String getHtmlPicture(String httpUrl, String filePath , String myFileName) {

        URL url;                        //定义URL对象url
        BufferedInputStream in;         //定义输入字节缓冲流对象in
        FileOutputStream file;          //定义文件输出流对象file
        try {
            String fileName = null == myFileName?httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", ""):myFileName; //图片文件名(null时用网络图片原名)
            url = new URL(httpUrl);     //初始化url对象
            in = new BufferedInputStream(url.openStream());                                 //初始化in对象，也就是获得url字节流
            //file = new FileOutputStream(new File(filePath +"\\"+ fileName));
            file = new FileOutputStream(mkdirsmy(filePath,fileName));
            int t;
            while ((t = in.read()) != -1) {
                file.write(t);
            }
            file.close();
            in.close();
            return fileName;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static File mkdirsmy(String dir, String realName) throws IOException{
        File file = new File(dir, realName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }
}
