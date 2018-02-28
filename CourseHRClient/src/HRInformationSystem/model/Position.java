package HRInformationSystem.model;

import java.io.Serializable;

public class Position implements Serializable {
    public int id;
    public String name;
    public String schedule;
    public int hours_per_week;
    public int average_salary;

    public Position(String schedule, int hours_per_week, int average_salary) {
        this.schedule = schedule;
        this.hours_per_week = hours_per_week;
        this.average_salary = average_salary;
    }

    public Position() {
    }

    public int getId() {
        return id;
    }



    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getHours_per_week() {
        return hours_per_week;
    }

    public void setHours_per_week(int hours_per_week) {
        this.hours_per_week = hours_per_week;
    }

    public int getAverage_salary() {
        return average_salary;
    }

    public void setAverage_salary(int average_salary) {
        this.average_salary = average_salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
