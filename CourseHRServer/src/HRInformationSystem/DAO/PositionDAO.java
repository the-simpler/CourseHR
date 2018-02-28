package HRInformationSystem.DAO;

import HRInformationSystem.model.Position;
import HRInformationSystem.model.Skills;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionDAO extends AbstractDAO<Position> {

    public PositionDAO(Connection connect) {
        super(connect);
    }

    public List<Position> showPosition() throws ClassNotFoundException {
        Statement st = null;
        try {
            List<Position> positionList;
            positionList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT * FROM position");
            //System.out.println(123);
            while (resSet.next()) {
                //System.out.println(123);
                Position position = new Position();
                position.id = resSet.getInt("id");
                position.name = resSet.getString("name");
                position.schedule = resSet.getString("schedule");
                position.average_salary = resSet.getInt("average_salary");
                position.hours_per_week = resSet.getInt("hours_per_week");
                positionList.add(index, position);
                //System.out.println(position.getAverage_salary()+position.hours_per_week+position.schedule);
                index++;
            }
            return positionList;
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


    public boolean edit(Position entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("UPDATE course.position SET  position.name= '" + entity.name+ "', position.schedule= '"+entity.schedule+"', position.hours_per_week ="+entity.hours_per_week+", position.average_salary="+entity.average_salary+" WHERE position.id = "+entity.id+";");
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

    public boolean create(Position entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("INSERT INTO course.position (name, schedule, hours_per_week,average_salary) VALUES ('" +entity.name+ "', '"+entity.schedule+"', "+entity.hours_per_week+", "+entity.average_salary+ ");");
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
    public boolean delete(Position entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("DELETE FROM position WHERE position.id ="+entity.id);
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