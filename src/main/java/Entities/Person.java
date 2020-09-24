package Entities;

public class Person {
    protected String personName;
    protected Integer personAge;

    public Person() {
        this.personName = "";
        this.personAge = 0;
    }

    public Person(String personName, Integer personAge) {
        this.personName = personName;
        this.personAge = personAge;
    }

    public String getPersonName() {
        return personName;
    }

    public Integer getPersonAge() {
        return personAge;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonage(Integer personAge) {
        this.personAge = personAge;
    }

    public String getData() {
        return String.format("%s, age %d", this.personName, this.personAge);
    }
}
