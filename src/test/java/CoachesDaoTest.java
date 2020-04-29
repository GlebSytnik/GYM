import dao.CoachDAO;
import dao.exception.DAOException;
import dao.impl.mysql.MysqlCoachDAO;
import entity.Coach;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoachesDaoTest {
    private CoachDAO coachDao = new MysqlCoachDAO();
    @Before
    public void beforeClass() throws Exception {
       MysqlCoachDAO coachDAO = new MysqlCoachDAO();
        coachDao.delete(21);
    }

    @Test
    public void testGetCoach() {
        coachDao.read(1);
    }

      @Test
  public void testInsertCoach() throws  DAOException {
        Coach coach = new Coach();
        coach.setuserdetailsId(4);
        coach.setTimetables_id(2);
        coach.setQualification("Высшая");
        coachDao.create(coach);
        try {
            assertNotNull(coachDao.read(5));
        }
        catch (AssertionError e){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateCoach() {
        coachDao.read(2);

        Coach coach = new Coach();
        coach.setId(2);
        coach.setTimetables_id(2);
        coach.setuserdetailsId(4);
        coach.setQualification("Средняя");

        coachDao.update(coach);
        assertEquals(coachDao.read(2).getId(), coach.getId());
        assertEquals(coachDao.read(2).getTimetablesid(), coach.getTimetablesid());
        assertEquals(coachDao.read(2).getUserDetailsId(), coach.getUserDetailsId());
        assertEquals(coachDao.read(2).getQualification(), coach.getQualification());
    }

    @Test
    public void testDeleteCoach() {
        coachDao.read(3);

        coachDao.delete(3);
        assertNull("we don't have this coach",coachDao.read(3));
    }
}


