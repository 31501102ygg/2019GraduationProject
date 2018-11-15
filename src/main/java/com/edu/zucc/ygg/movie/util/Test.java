package com.edu.zucc.ygg.movie.util;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.exception.ImgException;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.service.impl.UpImgServiceImpl;
import tk.mybatis.mapper.util.StringUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    public static void uploadMovieImg(Movie movie){
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://47.107.70.187:3306/movie?useUnicode=true&characterEncoding=utf8&useSSL=false";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "Ygg&cc970528";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            PreparedStatement ps = con.prepareStatement("insert into movie(" +
                    "name,foreign_name,img_url,release_time," +
                    "sheet_length,make_state,type,director,screenwriter," +
                    "actors,language,introduction)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,movie.getName());
            ps.setString(2,movie.getForeignName());
            ps.setString(3,movie.getImgUrl());
            ps.setDate(4, new java.sql.Date(movie.getReleaseTime().getTime()));
            ps.setInt(5,movie.getSheetLength());
            ps.setString(6,movie.getMakeState());
            ps.setString(7,movie.getType());
            ps.setString(8,movie.getDirector());
            ps.setString(9,movie.getScreenwriter());
            ps.setString(10,movie.getActors());
            ps.setString(11,movie.getLanguage());
            ps.setString(12,movie.getIntroduction());

            ps.execute();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

    public static List<Movie> searchMovieAll(){
        List<Movie> movieList = new ArrayList<>();
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://47.107.70.187:3306/movie?useUnicode=true&characterEncoding=utf8&useSSL=false";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "Ygg&cc970528";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            PreparedStatement ps = con.prepareStatement("select id,img_url from movie ");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setImgUrl(resultSet.getString("img_url"));
                movieList.add(movie);
            }
            con.close();

        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
        return movieList;
    }

    public static void updateMovieImg(Movie movie){
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://47.107.70.187:3306/movie?useUnicode=true&characterEncoding=utf8&useSSL=false";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "Ygg&cc970528";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            PreparedStatement ps = con.prepareStatement("update movie set img_url = ? where id = ?");
            ps.setString(1,movie.getImgUrl());
            ps.setInt(2,movie.getId());
            ps.execute();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

    public static void main(String[] args){
//        String img = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2516578307.jpg";
//        getHtmlPicture(img,filePath,null);
//        uploadImg("C:/Users/de'l'l/Desktop/testImg/p2233971046.jpg");
        List<Movie> movieList = Test.searchMovieAll();
        for (Movie movie:movieList){
            String filePath = "C:\\Users\\de'l'l\\Desktop\\testImg";
            String oldImgUrl = movie.getImgUrl();
            String fileName = getHtmlPicture(oldImgUrl,filePath,null);
            String newImgUrl = uploadImg("C:/Users/de'l'l/Desktop/testImg/"+fileName);
            if (newImgUrl!=null){
                movie.setImgUrl(newImgUrl);
                updateMovieImg(movie);
            }
        }
    }
    public static String getHtmlPicture(String httpUrl, String filePath , String myFileName) {

        URL url;                        //定义URL对象url
        BufferedInputStream in;         //定义输入字节缓冲流对象in
        FileOutputStream file;          //定义文件输出流对象file
        try {
            String fileName = null == myFileName?httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", ""):myFileName; //图片文件名(null时用网络图片原名)
            url = new URL(httpUrl);     //初始化url对象
            in = new BufferedInputStream(url.openStream());                                 //初始化in对象，也就是获得url字节流
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

    public static String uploadImg(String path){
        String url="";
        if (!StringUtil.isEmpty(path)){
            try {
                url = new UpImgServiceImpl().updateHead(path);
                System.out.println("电影图片上传到图片库成功"+url);
                return url;
            } catch (ImgException e) {
                System.out.println("电影图片上传到图片库失败");
            }
        }
        return null;
    }
}
