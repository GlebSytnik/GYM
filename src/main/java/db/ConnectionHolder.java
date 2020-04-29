package db;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHolder {
    private  static  final String DB_URL="jdbc:mysql://localhost:3306/gym?serverTimezone=UTC";
    private  static  final String DB_USERNAME="root";
    private  static  final String DB_PASSWORD="root";
    public ConnectionHolder(){

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
