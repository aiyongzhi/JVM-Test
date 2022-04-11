package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//JDBC的工具类
/*
 * 能够提供的方法
 * 1:获取数据库的连接池对象
 * 2:获取链接
 * 3:提供关闭操作
 * */
public class JDBCUtils {
    private static DataSource dataSource;

    private JDBCUtils() {
    }

    ;//只能通过类名直接引用类里面的方法

    static {
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pro = new Properties();
        try {
            pro.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /*
     * 用于执行JDBC编程时，对查找语句所使用资源的关闭
     * */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void close(Statement statement, Connection connection) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
