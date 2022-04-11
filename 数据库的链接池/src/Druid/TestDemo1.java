package Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
 * 1:加载Druid的jar包
 * 2:定义配置文件
 * 3:获取连接池对象
 * 4:获取链接
 * */
public class TestDemo1 {
    public static void main(String[] args) {
        Properties pro = new Properties();
        InputStream inputStream = TestDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        DataSource ds = null;
        try {
            pro.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);
            connection = ds.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
