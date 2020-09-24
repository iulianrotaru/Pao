package Entities;

import Entities.Person;

public class OtherJobs extends Person {
    private Integer yearsOfExperience;
    private Integer salary;
    private String professionName;

    public OtherJobs(Integer yearsOfExperience, Integer salary, String professionName, String personName, Integer personAge) {
        super(personName,personAge);
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.professionName = professionName;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }
}
