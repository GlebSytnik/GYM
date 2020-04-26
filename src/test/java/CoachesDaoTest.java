import dao.exception.DAOException;
import dao.impl.mysql.MysqlCoachDAO;
import db.ConnectionHolder;
import db.DBUtil;
import entity.Coach;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class CoachesDaoTest {
    private DBUtil dbUtils;
   // private CoachDaoImpl coachDaoImpl;
    private MysqlCoachDAO coachDao;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private ConnectionHolder connectionHolder;


    @Before
    public void setUp() throws Exception {
        connection = ConnectionHolder.getConnection();
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testGetCoach() throws DAOException {
        coachDao = new MysqlCoachDAO(connection);
        coachDao.read(1);
    }

    @Test
    public void testInsertCoach() throws DAOException {
        coachDao = new MysqlCoachDAO(connection);
        Coach coach = new Coach();
        coach.setId(4);
        coach.setuserdetailsId(4);
        coach.setTimetables_id(2);


        coachDao.create(coach);
        assertNotNull("we added someone", coachDao.read(4));
    }

    @Test
    public void testUpdateCoach() throws DAOException {
        coachDao = new MysqlCoachDAO(connection);
        coachDao.read(2);

        Coach coach = new Coach();
        coach.setId(2);
        coach.setTimetables_id(2);
        coach.setuserdetailsId(4);
        coach.setQualification("pidar");

        coachDao.update(coach);
        assertEquals(coachDao.read(2).getId(), coach.getId());
        assertEquals(coachDao.read(2).getTimetablesid(), coach.getTimetablesid());
        assertEquals(coachDao.read(2).getUserDetailsId(), coach.getUserDetailsId());
        assertEquals(coachDao.read(2).getQualification(), coach.getQualification());
    }

    @Test
    public void testDeleteCoach() throws DAOException {
        coachDao = new MysqlCoachDAO(connection);
        coachDao.read(3);

        coachDao.delete(3);
        assertNull("we don't have this coach",coachDao.read(3));
    }
}


