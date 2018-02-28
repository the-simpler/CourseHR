package HRInformationSystem.model;

import java.io.Serializable;

public class RequestJoin implements Serializable {
    public int id;
    public String description;
    public String position;
    public String client;
    public String manager;
    public String status;

    public RequestJoin(int id,String description, String position, String client, String manager) {
        this.id = id;
        this.description = description;
        this.position = position;
        this.client = client;
        this.manager = manager;
    }

    public RequestJoin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}