package com.edu.zucc.ygg.movie.util;

import com.edu.zucc.ygg.movie.domain.Movie;
import com.edu.zucc.ygg.movie.exception.ImgException;
import com.edu.zucc.ygg.movie.service.UpImgService;
import com.edu.zucc.ygg.movie.service.impl.UpImgServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.xmlunit.util.Nodes;
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
//        List<Movie> movieList = Test.searchMovieAll();
//        for (Movie movie:movieList){
//            String filePath = "C:\\Users\\de'l'l\\Desktop\\testImg";
//            String oldImgUrl = movie.getImgUrl();
//            String fileName = getHtmlPicture(oldImgUrl,filePath,null);
//            String newImgUrl = uploadImg("C:/Users/de'l'l/Desktop/testImg/"+fileName);
//            if (newImgUrl!=null){
//                movie.setImgUrl(newImgUrl);
//                updateMovieImg(movie);
//            }
//        }

        String content = "<p>骋和梅利两兄弟被强兽人抓走,亚拉冈和佛罗多兵分两路，分别前往末日山脉焚毁至尊魔戒和追赶并救援被抓的霍比特人，佛罗多和山姆一路，亚拉冈，金利，莱戈拉斯一路。佛罗多的食物不多，路线不明，征途迷茫，却发现了穷追不舍的史麦戈，后者渴望拿回他曾经拥有的魔戒。</p><p>佛罗多意识到史麦戈的悲惨遭遇，又得知史麦戈知道前往摩多的路线，自然的让他成为了向导。山姆是佛罗多的后勤管理人员，贴身保镖，迷茫时的开导者，可谓兢兢业业，没有他，佛罗多不可能成功地完成使命。亚拉冈这边呢，遇到了白袍甘道夫，发现两个霍比特人进入了树林，并参加了树人会议，后来成功摧毁了萨鲁曼的基地。甘道夫带领亚拉冈他们拯救了受到控制的落汗国王，并抵御兽人大军的侵略。</p><p>魔戒远征队的传奇故事告一段落，从头到尾，向我们展示了霍比特人的冒险，勇敢，重情重义的优良品格，以及甘道夫的运筹帷幄，亚拉冈的沉稳，男子汉的气概，精灵的能力出众，矮人的任劳任怨。也从一些人的言行中展示了贪婪，残忍的特点。不可否认，人性中好的坏的兼备，就连向来理性的佛罗多也会迷失，我们应该正视这一点。</p><p>忽然想到这一句</p><p><span class=\"fr-img-caption fr-fic fr-dib\" style=\"width: 300px;\"><span class=\"fr-img-wrap\"><img src=\"http://ygg-31501102-bucket.oss-cn-shenzhen.aliyuncs.com/commentary_img/1547908633647.jpg\"><span class=\"fr-inner\">测试图片<br><br></span></span></span></p><p>&quot;或许我们努力的活着不是去让这个世界善良而是不让自己丑恶。&quot;</p>";

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
