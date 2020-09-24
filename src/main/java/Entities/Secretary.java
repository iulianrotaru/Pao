package Entities;

import Entities.Person;

public class Secretary extends Person {
    private Integer yearsOfExperience;
    private Integer salary;
    private String program;

    public Secretary(Integer yearsOfExperience, Integer salary, String program, String personName, Integer personAge) {
        super(personName,personAge);
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.program = program;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getProgram() {
        return program;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
