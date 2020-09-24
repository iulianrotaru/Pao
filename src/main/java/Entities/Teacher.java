package Entities;

import Entities.Person;

public class Teacher extends Person {
    private Integer yearsOfExperience;
    private Integer salary;
    private String materieName;
    private String doesMeditate;
    private String whenMeditate;

    public Teacher(Integer yearsOfExperience, Integer salary, String materieName, String doesMeditate, String whenMeditate, String personName, Integer personAge) {
        super(personName,personAge);
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.materieName = materieName;
        this.doesMeditate = doesMeditate;
        this.whenMeditate = whenMeditate;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getMaterieName() {
        return materieName;
    }

    public String getDoesMeditate() {
        return doesMeditate;
    }

    public String getWhenMeditate() {
        return whenMeditate;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setMaterieName(String materieName) {
        this.materieName = materieName;
    }

    public void setDoesMeditate(String doesMeditate) {
        this.doesMeditate = doesMeditate;
    }

    public void setWhenMeditate(String whenMeditate) {
        this.whenMeditate = whenMeditate;
    }
}
