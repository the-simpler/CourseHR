package HRInformationSystem.model;


import java.io.Serializable;

public class Client implements Serializable {
    public int id;
    public String name;
    public String surname;
    public int age;
    public int skills;
    public String education;
    public int experience;
    public int user_id;

    public Client(String name, String surname, int age, int skills, String education, int experience) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
    }

    public Client() {
    }

    public int getId() {
        return id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSkills() {
        return skills;
    }

    public void setSkills(int skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
