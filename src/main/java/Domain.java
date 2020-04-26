import dao.exception.DAOException;
import dao.impl.mysql.MysqlUserDetailsDAO;
import db.ConnectionHolder;
import entity.UserDetails;

import java.sql.SQLException;

public class Domain {
    public static void main(String[] args) throws DAOException, SQLException {
        ConnectionHolder util=new ConnectionHolder();
        util.getConnection();
        //  Coach coach=new Coach();
        // coach.setId(4);
        //coach.setId(3);
        // coach.setQualification("LOhhyyyddd");
        // coach.setTimetables_id(1);
        // coach.setuserdetailsId(2);
        // coach.setId(4);
        UserDetails d= new UserDetails();
        d.setPhone("sfsffffddg");
        d.setPassword("dsggffh");
        d.setId(12);
        d.setAge(33);
        d.setLastName("asdhhh");
        d.setFirstName("dggaa");
        d.setLogin("wer");
        d.setAvatarReferense("sdffsss");
        MysqlUserDetailsDAO f =new MysqlUserDetailsDAO();
        f.create(d);
        // f.delete(12);


        // MysqlCoachDAO c = new MysqlCoachDAO();
        // c.update(coach);
        // c.delete(4);
        // c.create(coach);
        // Coach c1=null;
        //  c1=c.read(3);
        //System.out.print(c1.getQualification());

    }
}
