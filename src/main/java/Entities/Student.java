package Entities;

import Entities.Person;

public class Student extends Person {
    private String program;
    private Integer year;

    public Student(String program, Integer year, String personName, Integer personAge) {
        super(personName,personAge);
        this.program = program;
        this.year = year;
    }

    public String getProgram() {
        return program;
    }

    public Integer getYear() {
        return year;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
