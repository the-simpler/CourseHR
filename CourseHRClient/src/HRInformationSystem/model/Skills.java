package HRInformationSystem.model;

import java.io.Serializable;

public class Skills implements Serializable {
    public int id;
    public String name;

    public Skills(String name) {
        this.name = name;
    }

    public Skills() {
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
}
