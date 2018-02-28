package HRInformationSystem.DAO;

import HRInformationSystem.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";

    public UserDAO(Connection connect) {
        super(connect);
    }

    public List<User> findAll() {
        List<User> abonents = new ArrayList<>();
        Statement st = null;
        try {
            st = connect.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM `course`.`user`");
            while (resultSet.next()) {
                User user = new User();
                user.id = resultSet.getInt("id");
                user.login = resultSet.getString("login");
                user.password = resultSet.getString("password");
                user.role = resultSet.getInt("role");
                abonents.add(user);
            }
        } catch (SQLException e) {
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }

        }
        return abonents;

    }

    public boolean create(User entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("INSERT INTO course.user (login, password, role) VALUES ('" + entity.login + "', '" + entity.password+"', " +entity.role+ ");");
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

    public User check(User entity) throws ClassNotFoundException, SQLException {
        String find = "";
        Statement st = null;
        try {
            st = connect.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM user");
            while (resSet.next()) {
                User account = new User();
                account.login = resSet.getString("login");
                account.password = resSet.getString("password");
                if (account.login.equals(entity.login) && account.password.equals(entity.password)) {
                    account.id= resSet.getInt("id");
                    account.role = resSet.getInt("role");
                    return account;
                }
            }
        } catch (SQLException e) {
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
        return new User();
    }

    public int getId(User entity) throws ClassNotFoundException, SQLException {
        int find = -1;
        Statement st = null;
        try {
            st = connect.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM user");
            while (resSet.next()) {
                //int id = resSet.getInt("id");
                String login = resSet.getString("login");
                String password = resSet.getString("password");
                if (login.equals(entity.login) && password.equals(entity.password))
                    return resSet.getInt("id");
            }
        } catch (SQLException e) {
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.err.println("нулевой statement " + e);
                }
            }
        }
        return find;
    }

    public boolean edit(User entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("UPDATE course.user SET  user.login= '" + entity.login + "', user.password ='" + entity.password+"', user.role =" +entity.role+ " WHERE user.id = "+entity.id+";");
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

    public boolean delete(User entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("DELETE FROM user WHERE user.id ="+entity.id);
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
