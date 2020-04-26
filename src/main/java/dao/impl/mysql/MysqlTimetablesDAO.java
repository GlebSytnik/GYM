package dao.impl.mysql;

import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;
import entity.Timetables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MysqlTimetablesDAO implements TimetablesDAO {
    private static MysqlTimetablesDAO timetablesDAO;
    private static final Logger LOG = Logger.getLogger(MysqlSubscriptionDAO.class.getName());

    private static final String CREATE_TIMETABLES = "INSERT INTO timetables(id,datatime,datatime_2)VALUES(?,?,?)";
    private static final String DELETE_BY_ORDER_TIMETABLES = "DELETE  FROM timetables WHERE id=?";
    private static final String GET_TIMETABLES = "SELECT * FROM timetables WHERE id = ?";
    private static final String UPDATE_TIMETABLES= "UPDATE timetables SET  datatime=?,datatime_2=? WHERE id=?";
    static MysqlTimetablesDAO getInstance(){
        if(timetablesDAO==null){
            timetablesDAO=new MysqlTimetablesDAO();
        }
        return timetablesDAO;
    }
    private MysqlTimetablesDAO(){
    }
    @Override
    public Integer create(Timetables timetables) throws DAOException {
        Connection connection ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            connection=ConnectionHolder.getConnection();
            preparedStatement=connection.prepareStatement(CREATE_TIMETABLES,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,timetables.getId());
            preparedStatement.setString(++k,timetables.getDataTime());
            preparedStatement.setString(++k,timetables.getDataTime2());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating timetables failed, no ID obtained.");
            }

        } catch (SQLException e){
            LOG.info("Can not create timetables.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return key;
    }
    @Override
    public Timetables read(Integer key) throws DAOException {
        Timetables timetables=new Timetables();
        Connection connection;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection=ConnectionHolder.getConnection();
            preparedStatement=connection.prepareStatement(GET_TIMETABLES);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                timetables.setId(resultSet.getInt("id"));
                timetables.setDataTime(resultSet.getString("datatime"));
                timetables.setDataTime2(resultSet.getString("datatime_2"));
                return timetables;
            }
        }catch (SQLException e){
            LOG.info("Can not create timetables." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return null;
    }
    @Override
    public void update(Timetables timetables) throws  DAOException {
        PreparedStatement preparedStatement=null;
        Connection connection;
        try {
            connection=ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TIMETABLES);
            int k = 0;

            preparedStatement.setString(++k,timetables.getDataTime());
            preparedStatement.setString(++k,timetables.getDataTime2());
            preparedStatement.setInt(++k,timetables.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
    @Override
    public void delete(Integer key) throws  DAOException {
        PreparedStatement preparedStatement=null;
        Connection connection;
        try {
            connection= ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_TIMETABLES);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
}
