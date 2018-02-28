package HRInformationSystem.DAO;

import HRInformationSystem.model.Request;
import HRInformationSystem.model.Skills;
import HRInformationSystem.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillsDAO  extends AbstractDAO<Skills> {
    public SkillsDAO(Connection connect) {
        super(connect);
    }

    public List<Skills> getSkills() throws ClassNotFoundException, SQLException {
        Statement st = null;
        try {
            List<Skills> skillsList;
            skillsList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT * FROM skills");
            while (resSet.next()) {
                Skills skills = new Skills();
                skills.id = resSet.getInt("id");
                skills.name = resSet.getString("name");
                skillsList.add(index, skills);
                index++;
            }
            return skillsList;
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

    public boolean edit(Skills entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("UPDATE course.skills SET  skills.name= '" + entity.name+"' WHERE skills.id = "+entity.id+";");
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

    public boolean create(Skills entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("INSERT INTO course.skills (name) VALUES ('" +entity.name+"');");
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

    public boolean delete(Skills entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("DELETE FROM skills WHERE skills.id ="+entity.id);
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
