package dao.impl.mysql;

import dao.exception.DAOException;
import db.ConnectionHolder;
import db.DBUtil;
import entity.Customers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MysqlCustomersDAO implements CustomersDAO {
    private static MysqlCustomersDAO customersDAO;
    private static final Logger LOG = Logger.getLogger(MysqlCustomersDAO.class.getName());

    private static final String CREATE_CUSTOMERS = "INSERT INTO customers(id,height,weight,subscription_idsubscription_fk,user_details_iduser_details,coach_idcoach)VALUES(?,?,?,?,?,?)";
    private static final String DELETE_BY_ORDER_CUSTOMERS = "DELETE  FROM customers WHERE id=?";
    private static final String GET_CUSTOMERS = "SELECT * FROM сustomers WHERE id = ?";
    private static final String UPDATE_CUSTOMERS= "UPDATE customers SET  height=?,weight=?,subscription_idsubscription_fk=?,user_details_iduser_details=?,coach_idcoach=? WHERE id=?";
    static MysqlCustomersDAO getInstance(){
        if(customersDAO==null){
            customersDAO=new MysqlCustomersDAO();
        }
        return customersDAO;
    }
    private MysqlCustomersDAO(){
    }
    @Override
    public Integer create(Customers customers) throws DAOException {
        Connection connection ;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Integer key;
        try{
            connection=ConnectionHolder.getConnection();
            preparedStatement=connection.prepareStatement(CREATE_CUSTOMERS,preparedStatement.RETURN_GENERATED_KEYS);
            int k=0;
            preparedStatement.setInt(++k,customers.getId());
            preparedStatement.setInt(++k,customers.getHeight());
            preparedStatement.setInt(++k,customers.getWeight());
            preparedStatement.setInt(++k,customers.getSubscriptionId());
            preparedStatement.setInt(++k,customers.getUserDetailsId());
            preparedStatement.setInt(++k,customers.getCoachId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                key = resultSet.getInt(1);
            } else {
                throw new SQLException("Creating customer failed, no ID obtained.");
            }

        } catch (SQLException e){
            LOG.info("Can not create customers.");
            throw new DAOException(e.getMessage(), e);
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return key;
    }
    @Override
    public Customers read(Integer key) throws DAOException  {
        Customers customers=new Customers();
        Connection connection;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            connection=ConnectionHolder.getConnection();
            preparedStatement=connection.prepareStatement(GET_CUSTOMERS);
            preparedStatement.setInt(1,key);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                customers.setId(resultSet.getInt("id"));
                customers.setHeight(resultSet.getInt("height"));
                customers.setWeight(resultSet.getInt("weight"));
                customers.setSubscriptionId(resultSet.getInt("subscription_idsubscription_fk"));
                customers.setUserdetailsId(resultSet.getInt("user_details_iduser_details"));
                customers.setCoachId(resultSet.getInt("coach_idcoach"));
                return customers;
            }
        }catch (SQLException e){
            LOG.info("Can not read customers." );
            throw new DAOException(e.getMessage(), e);
        }finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(preparedStatement);
        }
        return null;
    }
    @Override
    public void update(Customers customers) throws  DAOException {
        PreparedStatement preparedStatement=null;
        Connection connection;
        try {
            connection=ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS);
            int k = 0;

            preparedStatement.setInt(++k,customers.getHeight());
            preparedStatement.setInt(++k,customers.getWeight());
            preparedStatement.setInt(++k,customers.getSubscriptionId());
            preparedStatement.setInt(++k,customers.getUserDetailsId());
            preparedStatement.setInt(++k,customers.getCoachId());
            preparedStatement.setInt(++k,customers.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }
    @Override
    public void delete(Integer key) throws DAOException   {
        PreparedStatement preparedStatement=null;
        Connection connection;
        try {
            connection= ConnectionHolder.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_CUSTOMERS);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.closeStatement(preparedStatement);
        }

    }




}