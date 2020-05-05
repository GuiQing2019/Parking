package net.com.parkingos.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ASUS
 * @date 2020/4/6
 */
public class JdbcUtils {

    //获取配置值
    public static String DB_Driver="";
    public static String DB_URL="";
    public static String DB_USER="";
    public static String DB_PWD="";



    static {
        //初始化连接

        //创建配置对象
        Properties pro = new Properties();

        InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            pro.load(in);
            DB_Driver=pro.getProperty("driver");
            DB_URL=pro.getProperty("url");
            DB_USER=pro.getProperty("username");
            DB_PWD=pro.getProperty("password");

            Class.forName(DB_Driver);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** 获取数据库对象
     * @return
     */
    public static Connection getcon(){

        try {
            return DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**关闭数据库连接
     * @param autoCloseables
     */
    public static void closeAll(AutoCloseable ... autoCloseables){

        if (autoCloseables!=null){


            for (AutoCloseable autoCloseable : autoCloseables) {
                try {
                    autoCloseable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
