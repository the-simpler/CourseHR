package HRInformationSystem.model;

import java.io.Serializable;

public class Manager implements Serializable {
    public int id=1;
    public String name;
    public String surname;
    public int user_id;

    public Manager(String name, String surname) {

        this.name = name;
        this.surname = surname;

    }

    public Manager() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}