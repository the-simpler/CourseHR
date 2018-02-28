package HRInformationSystem.model;

import java.io.Serializable;

public class Request implements Serializable {

        public int id;
        public String description;
        public int position;
        public int manager;
        public int client;
        public String status;

        public Request(int id, String description, int position, int manager, int client) {
            this.id = id;
            this.description = description;
            this.position = position;
            this.manager = manager;
            this.client = client;
        }

        public Request() {
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

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getManager() {
            return manager;
        }

        public void setManager(int manager) {
            this.manager = manager;
        }

        public int getClient() {
            return client;
        }

        public void setClient(int client) {
            this.client = client;
        }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
