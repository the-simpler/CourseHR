package HRInformationSystem.DAO;

import HRInformationSystem.model.Manager;
import HRInformationSystem.model.Request;
import HRInformationSystem.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO extends AbstractDAO<Manager>  {

    public ManagerDAO(Connection connect) {
        super(connect);
    }
    public boolean create(Manager entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("INSERT INTO `course`.`manager` (`name`, `surname`, `user_id`) VALUES ('" + entity.name + "', '" + entity.surname+ "', " + String.valueOf(entity.user_id)+");");
        } catch (SQLException e) {

            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }
    public List<Manager> showManager() throws ClassNotFoundException, SQLException {
        Statement st = null;
        try {
            List<Manager> managerList;
            managerList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT * FROM manager");
            while (resSet.next()) {
                Manager manager = new Manager();
                manager.id = resSet.getInt("id");
                manager.name = resSet.getString("name");
                manager.surname = resSet.getString("surname");
                manager.user_id = resSet.getInt("user_id");
                managerList.add(index, manager);
                index++;
            }
            return managerList;
        } catch (SQLException e) {
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return new ArrayList<>();
    }

    public boolean edit(Manager entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("UPDATE course.manager SET  manager.name= '" + entity.name+ "', manager.surname= '"+entity.surname+"', manager.user_id ="+entity.user_id+" WHERE manager.id = "+entity.id+";");
        } catch (SQLException e) {
            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }

    public boolean delete(Manager entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("DELETE FROM manager WHERE manager.id ="+entity.id);
        } catch (SQLException e) {

            return false;
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return true;
    }
}
