package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DBUtil {
    private static final Logger LOG = Logger.getLogger(DBUtil.class.getName());

    public static void rollback(Connection connection){
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOG.info("Can't rollback transaction");
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                LOG.info("Try close connection.");
                connection.close();
            } catch(SQLException e){
                LOG.info("Can not close connection.");
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                LOG.info("Try close statement.");
                statement.close();
            } catch (SQLException e) {
                LOG.info("Can not close statement.");
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                LOG.info("Try close ResultSet.");
                resultSet.close();
            } catch (SQLException e) {
                LOG.info("Can not ResultSet.");
            }
        }
    }
}

