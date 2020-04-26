package db;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHolder {
    private  static  final String DB_DRIVER="com.mysql.cj.jdbc.Driver";
    private  static  final String DB_URL="jdbc:mysql://localhost:3306/gym?serverTimezone=UTC";
    private  static  final String DB_USERNAME="root";
    private  static  final String DB_PASSWORD="root";
    public ConnectionHolder(){

    }
    /* public static Connection getConnection()  {
         Connection connection=null;
         try {
             Class.forName(DB_DRIVER);
             connection= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
             System.out.print("Connection ok");
         } catch (ClassNotFoundException  | SQLException v) {
             v.printStackTrace();
             System.out.print("Error");
         }
         return connection;
     }*/
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    public static void setConnection(Connection connection){
        connectionHolder.set(connection);
    }

    private static BasicDataSource basicDataSource = new BasicDataSource();
    static {
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(DB_USERNAME);
        basicDataSource.setPassword(DB_PASSWORD);
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(10);
        basicDataSource.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }


}
