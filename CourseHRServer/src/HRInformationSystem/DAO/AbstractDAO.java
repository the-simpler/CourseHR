package HRInformationSystem.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO <T> {
    protected Connection connect;

    public AbstractDAO(Connection connect) {
        this.connect = connect;
    }


    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println("Закончили");
        }
    }
}