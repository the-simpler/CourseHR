package HRInformationSystem.DAO;

import HRInformationSystem.model.Request;
import HRInformationSystem.model.RequestJoin;
import HRInformationSystem.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RequestDAO extends AbstractDAO<Request> {
    public static final String SQL_SELECT_ALL_REQUESTS = "SELECT * FROM request";

    public RequestDAO(Connection connect) {
        super(connect);
    }

    public List<Request> showRequests() throws ClassNotFoundException, SQLException {
        Statement st = null;
        try {
            List<Request> requestList;
            requestList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT * FROM request");
            while (resSet.next()) {
                Request request = new Request();
                request.client = resSet.getInt("client");
                request.description = resSet.getString("description");
                request.id = resSet.getInt("id");
                request.position = resSet.getInt("position");
                request.manager = resSet.getInt("manager");
                request.status = resSet.getString("status");
                //System.out.println(request.description);
                requestList.add(index, request);
                index++;
                }
            return requestList;
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
    public List<RequestJoin>  joinRequests() throws ClassNotFoundException, SQLException {
        Statement st = null;
        try {
            List<RequestJoin> joinList;
            joinList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT client.name, client.surname,request.description,request.id,position.name,manager.name,manager.surname, request.status FROM (((request INNER JOIN client ON request.client = client.id) INNER JOIN manager ON request.manager = manager.id)INNER JOIN position ON request.position = position.id)");
            while (resSet.next()) {
                RequestJoin request = new RequestJoin();
                request.client = resSet.getString("client.surname")+" "+resSet.getString("client.name");
                request.description = resSet.getString("request.description");
                request.id = resSet.getInt("request.id");
                request.position = resSet.getString("position.name");
                request.manager = resSet.getString("manager.surname")+" "+resSet.getString("manager.name");
                request.status = resSet.getString("status");
                System.out.println(request.description + request.client+request.manager+request.position+request.id);
                joinList.add(index, request);
                index++;
            }
            return joinList;
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

    public boolean edit(Request entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("UPDATE course.request SET request.status= '" + entity.status + "', request.description= '" + entity.description + "', request.position= " + entity.position + ", request.manager =" + entity.manager + ", request.client=" + entity.client + " WHERE request.id = " + entity.id + ";");
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

    public boolean create(Request entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("INSERT INTO course.request (description, client, manager, position, status) VALUES ('" +entity.description+ "', "+entity.client+", "+entity.manager+", "+entity.position+", '"+entity.status+ "');");
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
    public boolean delete(Request entity) {
        Statement st = null;
        try {
            st = connect.createStatement();
            st.execute("DELETE FROM request WHERE request.id ="+entity.id);
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


    public List<RequestJoin>  joinRequestsByUser(int user_id) throws ClassNotFoundException, SQLException {
        Statement st = null;
        try {
            List<RequestJoin> joinList;
            joinList = new ArrayList<>();
            st = connect.createStatement();
            int index = 0;
            ResultSet resSet = st.executeQuery("SELECT client.name, client.surname,request.description,request.id,position.name,manager.name,manager.surname, request.status FROM (((request INNER JOIN client ON request.client = client.id) INNER JOIN manager ON request.manager = manager.id)INNER JOIN position ON request.position = position.id) WHERE request.client="+user_id);
            while (resSet.next()) {
                RequestJoin request = new RequestJoin();
                request.client = resSet.getString("client.surname")+resSet.getString("client.name");
                request.description = resSet.getString("request.description");
                request.id = resSet.getInt("request.id");
                request.position = resSet.getString("position.name");
                request.manager = resSet.getString("manager.surname")+resSet.getString("manager.name");
                request.status = resSet.getString("status");
                System.out.println(request.description + request.client+request.manager+request.position+request.id);
                joinList.add(index, request);
                index++;
            }
            return joinList;
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
}
