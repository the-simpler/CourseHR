package HRInformationSystem.model;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String login;
    public String password;
    public int role;

    public User() {
    }
    public User(User temp) {
        this.login=temp.login;
        this.password= temp.password;
        this.role=temp.role;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        role = 1;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
