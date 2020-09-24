package Entities;

import Services.AuditWriterCSV;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.util.ArrayList;

public class Faculty {
    private String facultyName;
    private ArrayList<Teacher> listOfTeachers;
    private ArrayList<Laborant> listOfLaborants;
    private ArrayList<Secretary> listOfSecretaries;
    private ArrayList<Student> listOfStudents;
    private ArrayList<OtherJobs> listOfOtherJobs;

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
        listOfTeachers = new ArrayList<Teacher>();
        listOfLaborants = new ArrayList<Laborant>();
        listOfSecretaries = new ArrayList<Secretary>();
        listOfStudents = new ArrayList<Student>();
        listOfOtherJobs = new ArrayList<OtherJobs>();
    }

    public String getFacultyName() {
        return facultyName;
    }

    public ArrayList<Teacher> getListOfTeachers() {
        return listOfTeachers;
    }

    public ArrayList<Laborant> getListOfLaborants() {
        return listOfLaborants;
    }

    public ArrayList<Secretary> getListOfSecretaries() {
        return listOfSecretaries;
    }

    public ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

    public ArrayList<OtherJobs> getListOfOtherJobs() {
        return listOfOtherJobs;
    }

    public Teacher getTeacher(String nameTeacher) {
        Teacher result = null;

        for (Teacher teacher : listOfTeachers) {
            if (teacher.getPersonName().equals(nameTeacher)) {
                result = teacher;
                break;
            }
        }

        return result;
    }

    public Laborant getLaborant(String nameLaborant) {
        Laborant result = null;

        for (Laborant laborant : listOfLaborants) {
            if (laborant.getPersonName().equals(nameLaborant)) {
                result = laborant;
                break;
            }
        }

        return result;
    }

    public Secretary getSecretary(String nameSecretary) {
        Secretary result = null;

        for (Secretary secretary : listOfSecretaries) {
            if (secretary.getPersonName().equals(nameSecretary)) {
                result = secretary;
                break;
            }
        }

        return result;
    }

    public Student getStudent(String nameStudent) {
        Student result = null;

        for (Student student : listOfStudents) {
            if (student.getPersonName().equals(nameStudent)) {
                result = student;
                break;
            }
        }

        return result;
    }

    public OtherJobs getOtherJobs(String nameOtherJobs) {
        OtherJobs result = null;

        for (OtherJobs otherJobs : listOfOtherJobs) {
            if (otherJobs.getPersonName().equals(nameOtherJobs)) {
                result = otherJobs;
                break;
            }
        }

        return result;
    }

    public void deleteTeacher(String teacherName) {
        ArrayList<Teacher> listOfTeachersNEW = new ArrayList<Teacher>();

        for(Teacher teacher : listOfTeachers) {
            if(teacher.getPersonName().equals(teacherName)) {

            }
            else {
                listOfTeachersNEW.add(teacher);
            }
        }

        this.listOfTeachers = listOfTeachersNEW;
    }

    public void deleteLaborant(String laborantName) {
        ArrayList<Laborant> listOfLaborantsNEW = new ArrayList<Laborant>();

        for(Laborant laborant : listOfLaborants) {
            if(laborant.getPersonName().equals(laborantName)) {

            }
            else {
                listOfLaborantsNEW.add(laborant);
            }
        }

        this.listOfLaborants = listOfLaborantsNEW;
    }

    public void deleteSecretary(String secretaryName) {
        ArrayList<Secretary> listOfSecretariesNEW = new ArrayList<Secretary>();

        for(Secretary secretary : listOfSecretaries) {
            if(secretary.getPersonName().equals(secretaryName)) {

            }
            else {
                listOfSecretariesNEW.add(secretary);
            }
        }

        this.listOfSecretaries = listOfSecretariesNEW;
    }

    public void deleteStudent(String studentName) {
        ArrayList<Student> listOfStudentsNEW = new ArrayList<Student>();

        for(Student student : listOfStudents) {
            if(student.getPersonName().equals(studentName)) {

            }
            else {
                listOfStudentsNEW.add(student);
            }
        }

        this.listOfStudents = listOfStudentsNEW;
    }

    public void deleteOtherJobs(String otherJobsName) {
        ArrayList<OtherJobs> listOfOtherJobsNEW = new ArrayList<OtherJobs>();

        for(OtherJobs otherJobs : listOfOtherJobs) {
            if(otherJobs.getPersonName().equals(otherJobsName)) {

            }
            else {
                listOfOtherJobsNEW.add(otherJobs);
            }
        }

        this.listOfOtherJobs = listOfOtherJobsNEW;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public void addTeacher(Teacher teacher) {
        this.listOfTeachers.add(teacher);
    }

    public void addLaborant(Laborant laborant) {
        this.listOfLaborants.add(laborant);
    }

    public void addSecretary(Secretary secretary) {
        this.listOfSecretaries.add(secretary);
    }

    public void addStudent(Student student) {
        this.listOfStudents.add(student);
    }

    public void addOtherJobs(OtherJobs otherJobs) {
        this.listOfOtherJobs.add(otherJobs);
    }

    public Integer getNumberTeachers() {
        return this.listOfTeachers.size();
    }

    public Integer getNumberLaborants() {
        return this.listOfLaborants.size();
    }

    public Integer getNumberSecretaries() {
        return this.listOfSecretaries.size();
    }

    public Integer getNumberStudents() {
        return this.listOfStudents.size();
    }

    public Integer getNumberOtherJobs() {
        return this.listOfOtherJobs.size();
    }

    public Integer getNumberOfEmployees() throws IOException {
        AuditWriterCSV.printAuditAction("getNumberOfEmployees");

        return this.getNumberTeachers() + this.getNumberLaborants() + this.getNumberSecretaries() + this.getNumberOtherJobs();
    }

    public Integer getNumberOfPersons() throws IOException {
        AuditWriterCSV.printAuditAction("getNumberOfPersons");

        return this.getNumberStudents() + this.getNumberOfEmployees();
    }

    public void printListOfTeachers() throws IOException {
        AuditWriterCSV.printAuditAction("printListOfTeachers");

        System.out.println("List of teachers at faculty " + this.facultyName + ":");
        for(Teacher teacher : this.listOfTeachers) {
            System.out.println(teacher.getData());
        }
    }

    public void printListOfLaborants() throws IOException {
        AuditWriterCSV.printAuditAction("printListOfLaborants");

        System.out.println("List of laborants at faculty " + this.facultyName + ":");
        for(Laborant laborant : this.listOfLaborants) {
            System.out.println(laborant.getData());
        }
    }

    public void printListOfSecretaries() throws IOException {
        AuditWriterCSV.printAuditAction("printListOfSecretaries");

        System.out.println("List of secretaries at faculty " + this.facultyName + ":");
        for(Secretary secretary : this.listOfSecretaries) {
            System.out.println(secretary.getData());
        }
    }

    public void printListOfStudents() throws IOException {
        AuditWriterCSV.printAuditAction("printListOfStudents");

        System.out.println("List of students at faculty " + this.facultyName + ":");
        for(Student student : this.listOfStudents) {
            System.out.println(student.getData());
        }
    }

    public void printListOfOtherJobs() throws IOException {
        AuditWriterCSV.printAuditAction("printListOfOtherJobs");

        System.out.println("List of other jobs people at faculty " + this.facultyName + ":");
        for(OtherJobs otherJobs : listOfOtherJobs) {
            System.out.println(otherJobs.getData());
        }
    }

    public void printFacultyEmployees() throws IOException {
        AuditWriterCSV.printAuditAction("printFacultyEmployees");

        System.out.println(String.format("Printing the %d employees of faculty %s", this.getNumberOfEmployees(), this.facultyName));
        this.printListOfTeachers();
        this.printListOfLaborants();
        this.printListOfSecretaries();
        this.printListOfOtherJobs();
    }

    public Integer getSumOfSalaries() throws IOException {
        AuditWriterCSV.printAuditAction("getSumOfSalaries");

        Integer result = 0;

        for(Teacher teacher : this.listOfTeachers) {
            result += teacher.getSalary();
        }

        for(Laborant laborant : this.listOfLaborants) {
            result += laborant.getSalary();
        }

        for(Secretary secretary : this.listOfSecretaries) {
            result += secretary.getSalary();
        }

        for(OtherJobs otherJobs : this.listOfOtherJobs) {
            result += otherJobs.getSalary();
        }

        return result;
    }
}
