package Entities;

public class Laborant extends Person {
    private Integer yearsOfExperience;
    private Integer salary;
    private String laboratorName;
    private String doesHaveAnotherJob;

    public Laborant(Integer yearsOfExperience, Integer salary, String laboratorName, String doesHaveAnotherJob, String personName, Integer personAge) {
        super(personName,personAge);
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.laboratorName = laboratorName;
        this.doesHaveAnotherJob = doesHaveAnotherJob;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getLaboratorName() {
        return laboratorName;
    }

    public String getDoesHaveAnotherJob() {
        return doesHaveAnotherJob;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setLaboratorName(String laboratorName) {
        this.laboratorName = laboratorName;
    }

    public void setDoesHaveAnotherJob(String doesHaveAnotherJob) {
        this.doesHaveAnotherJob = doesHaveAnotherJob;
    }
}
