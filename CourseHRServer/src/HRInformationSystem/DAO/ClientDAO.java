package HRInformationSystem.DAO;

import HRInformationSystem.model.Client;
import HRInformationSystem.model.Manager;
import HRInformationSystem.model.Request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client>  {


    public ClientDAO(Connection connect) {
        super(connect);
    }


    public boolean create(Client entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("INSERT INTO `course`.`client` (`name`, `surname`,age,skills,education,experience ,`user_id`) VALUES ('" + entity.name + "', '"
                    + entity.surname+ "', "
                    + entity.age+", "
                    + entity.skills+",'"
                    + entity.education+"',"
                    + entity.experience+", "
                    + entity.user_id+");");
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

    public boolean edit(Client entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("UPDATE course.client SET  " +
                    "client.name= '" + entity.name+
                    "', client.surname= '"+entity.surname+
                    "', client.user_id ="+entity.user_id+
                    ", client.age ="+entity.age +
                    ", client.experience ="+entity.experience+
                    ", client.education ='"+entity.education+
                    "', client.skills ="+entity.skills+
                    " WHERE client.id = "+entity.id+";");
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
    public boolean delete(Client entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("DELETE FROM client WHERE client.id ="+entity.id);
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

    public List<Client> showClient() throws ClassNotFoundException, SQLException {
        Statement st = null;
        try {
            List<Client> clientList;
            clientList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT * FROM client");
            while (resSet.next()) {
                Client client = new Client();
                client.name = resSet.getString("name");
                client.surname = resSet.getString("surname");
                client.education = resSet.getString("education");
                client.id = resSet.getInt("id");
                client.age = resSet.getInt("age");
                client.skills = resSet.getInt("skills");
                client.experience = resSet.getInt("experience");
                client.user_id = resSet.getInt("user_id");
                clientList.add(index, client);
                index++;
            }
            return clientList;
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

    public Client getClient(int usr_id) {
        Statement st = null;
        try {
            st = connect.createStatement();
            ResultSet resSet = st.executeQuery("SELECT * FROM client WHERE client.user_id ="+usr_id+" LIMIT 1");
            resSet.next();
            Client client = new Client();
            client.name = resSet.getString("name");
            client.surname = resSet.getString("surname");
            client.education = resSet.getString("education");
            client.id = resSet.getInt("id");
            client.age = resSet.getInt("age");
            client.skills = resSet.getInt("skills");
            client.experience = resSet.getInt("experience");
            client.user_id = resSet.getInt("user_id");
            return client;
        } catch (SQLException e) {

            return new Client();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
        }

    }

}
