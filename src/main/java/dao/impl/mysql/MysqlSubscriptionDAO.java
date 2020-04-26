package dao.impl.mysql;

import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;
import entity.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MysqlSubscriptionDAO implements SubscriptionDAO {
    private static MysqlSubscriptionDAO subscriptionDAO;
    private static final Logger LOG = Logger.getLogger(MysqlSubscriptionDAO.class.getName());

    private static final String CREATE_SUBSCRIPTION = "INSERT INTO subscription(id,type_subscription)VALUES(?,?)";
    private static final String DELETE_BY_ORDER_SUBSCRIPTION = "DELETE  FROM subscription WHERE id=?";
    private static final String GET_SUBSCRIPTION = "SELECT * FROM subscription WHERE id = ?";
    private static final String UPDATE_SUBSCRIPTION= "UPDATE subscription SET  type_subscription=?, WHERE id=?";
    static MysqlSubscriptionDAO getInstance(){
        if(subscriptionDAO==null){
            subscriptionDAO=new MysqlSubscriptionDAO();
        }
        return subscriptionDAO;
    }
    private MysqlSubscriptionDAO(){
    }
    @Override
    public Integer create(Subscription subscription) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            connection= ConnectionHolder.getConnection();
            preparedStatement=connection.prepareStatement(CREATE_SUBSCRIPTION,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,subscription.getId());
            preparedStatement.setString(++k,subscription.getTypesubscription());
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }

        } catch (SQLException e){
            LOG.info("Can not create bill.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return key;
    }
    @Override
    public Subscription read(Integer key) throws DAOException  {
        Subscription subscription=new Subscription();
        Connection connection;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection=ConnectionHolder.getConnection();
            preparedStatement=connection.prepareStatement(GET_SUBSCRIPTION);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                subscription.setId(resultSet.getInt("id"));
                subscription.setTypesubscription(resultSet.getString("type_subscription"));
                return subscription;
            }
        }catch (SQLException e){
            LOG.info("Can not create subscription." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return null;
    }
    @Override
    public void update(Subscription subscription) throws  DAOException {
        PreparedStatement preparedStatement=null;
        Connection connection;
        try {
            connection=ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SUBSCRIPTION);
            int k = 0;

            preparedStatement.setString(++k,subscription.getTypesubscription());
            preparedStatement.setInt(++k,subscription.getId());
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
            connection=ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_SUBSCRIPTION);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
}