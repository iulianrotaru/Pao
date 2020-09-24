package Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import Entities.*;

public class World {
    private ArrayList<Country> listOfCountries;

    public World() {
        this.listOfCountries = new ArrayList<Country>();
    }

    public void addCountry(Country country) {
        listOfCountries.add(country);
    }

    public ArrayList<Country> getListOfCountries() {
        return listOfCountries;
    }

    public void printCountries() throws IOException {
        AuditWriterCSV.printAuditAction("printCountries");
        System.out.println("List of countries: ");
        for (Country country : listOfCountries) {
            System.out.println(country.getCountryName());
        }
    }

    public Country getCountry(String countryName) {
        Country result = null;
        for (Country country : listOfCountries) {
            if (country.getCountryName().equals(countryName)) {
                result = country;
                break;
            }
        }
        return result;
    }

    public void doesThisCountryExist(String countryName) throws IOException {
        AuditWriterCSV.printAuditAction("doesThisCountryExist");

        for (Country country : listOfCountries) {
            if (country.getCountryName().equals(countryName)) {
                System.out.println("Yes, country " + countryName + " exist!");
                return;
            }
        }
        System.out.println("No, country " + countryName + " doesn't exist!");
    }

    public String ThisCountryExist(String countryName) throws IOException {
        AuditWriterCSV.printAuditAction("ThisCountryExist");

        for (Country country : listOfCountries) {
            if (country.getCountryName().equals(countryName)) {
                System.out.println("Yes, country " + countryName + " exist!");
                return "Yes";
            }
        }
        System.out.println("No, country " + countryName + " doesn't exist!");
        return "No";
    }

    public String ThisFacultyExist(String facultyName) throws IOException {
        AuditWriterCSV.printAuditAction("ThisFacultyExist");

        for (Country country : listOfCountries) {
            for (Faculty faculty : country.getFaculties()) {
                if (faculty.getFacultyName().equals(facultyName)) {
                    System.out.println("Yes, faculty " + facultyName + " exist!");
                    return "Yes";
                }
            }
        }
        System.out.println("No, faculty " + facultyName + " doesn't exist!");
        return "No";
    }

    public void printAllInfo() throws IOException {
        AuditWriterCSV.printAuditAction("printAllInfo");

        for (Country country : listOfCountries) {
            System.out.println("Country : " + country.getCountryName());

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                System.out.println("Faculty : " + faculty.getFacultyName());

                System.out.println("List of teachers : ");
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    System.out.println("Name: " + teacher.getPersonName() + " Age: " + teacher.getPersonAge() + " Years of experience: " + teacher.getYearsOfExperience() + " Salary: " + teacher.getSalary() + " MaterieName: " + teacher.getMaterieName() + " DoesMeditate: " + teacher.getDoesMeditate() + " WhenMeditate: " + teacher.getWhenMeditate());
                }

                System.out.println("List of laborants : ");
                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    System.out.println("Name: " + laborant.getPersonName() + " Age: " + laborant.getPersonAge() + " Years of experience: " + laborant.getYearsOfExperience() + " Salary: " + laborant.getSalary() + " LaboratorName: " + laborant.getLaboratorName() + " doesHaveAnotherJob: " + laborant.getDoesHaveAnotherJob());
                }

                System.out.println("List of secretaries : ");
                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    System.out.println("Name: " + secretaries.getPersonName() + " Age: " + secretaries.getPersonAge() + " Years of experience: " + secretaries.getYearsOfExperience() + " Salary: " + secretaries.getSalary() + " Program: " + secretaries.getProgram());
                }

                System.out.println("List of students : ");
                ArrayList<Student> listOfStudents = faculty.getListOfStudents();
                for (Student students : listOfStudents) {
                    System.out.println("Name: " + students.getPersonName() + " Age: " + students.getPersonAge() + " Program: " + students.getProgram() + " Year: " + students.getYear());
                }

                System.out.println("List of otherjobs : ");
                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    System.out.println("Name: " + otherjobs.getPersonName() + " Age: " + otherjobs.getPersonAge() + " Years of experience: " + otherjobs.getYearsOfExperience() + " Salary: " + otherjobs.getSalary() + " professionName: " + otherjobs.getProfessionName());
                }
            }
        }
    }

    /*
    Schelet

    for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {

                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {

                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {

                }

                ArrayList<Student> listOfStudents = faculty.getListOfStudents();
                for (Student students : listOfStudents) {

                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {

                }
            }
        }
     */

    public void printLowestSalaryinWorld() throws IOException {
        AuditWriterCSV.printAuditAction("printLowestSalaryinWorld");

        Integer LowestSalary = 999999999;
        for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    if(LowestSalary > teacher.getSalary()) {
                        LowestSalary = teacher.getSalary();
                    }
                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    if(LowestSalary > laborant.getSalary()) {
                        LowestSalary = laborant.getSalary();
                    }
                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    if(LowestSalary > secretaries.getSalary()) {
                        LowestSalary = secretaries.getSalary();
                    }
                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    if(LowestSalary > otherjobs.getSalary()) {
                        LowestSalary = otherjobs.getSalary();
                    }
                }
            }
        }

        if(LowestSalary == 999999999) {
            System.out.println("There are no employees!");
        }
        else {
            System.out.println("Lowest Salary in world is: " + LowestSalary);
        }
    }

    public String getLowestSalaryinWorld() throws IOException {
        AuditWriterCSV.printAuditAction("printLowestSalaryinWorld");

        Integer LowestSalary = 999999999;
        for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    if(LowestSalary > teacher.getSalary()) {
                        LowestSalary = teacher.getSalary();
                    }
                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    if(LowestSalary > laborant.getSalary()) {
                        LowestSalary = laborant.getSalary();
                    }
                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    if(LowestSalary > secretaries.getSalary()) {
                        LowestSalary = secretaries.getSalary();
                    }
                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    if(LowestSalary > otherjobs.getSalary()) {
                        LowestSalary = otherjobs.getSalary();
                    }
                }
            }
        }

        if(LowestSalary == 999999999) {
            return "No employees!";
            //System.out.println("There are no employees!");
        }
        else {
            return Integer.toString(LowestSalary);
            //System.out.println("Lowest Salary in world is: " + LowestSalary);
        }
    }

    public void printBigestSalaryinWorld() throws IOException {
        AuditWriterCSV.printAuditAction("printBigestSalaryinWorld");

        Integer BigestSalary = -999999999;
        for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    if(BigestSalary < teacher.getSalary()) {
                        BigestSalary = teacher.getSalary();
                    }
                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    if(BigestSalary < laborant.getSalary()) {
                        BigestSalary = laborant.getSalary();
                    }
                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    if(BigestSalary < secretaries.getSalary()) {
                        BigestSalary = secretaries.getSalary();
                    }
                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    if(BigestSalary < otherjobs.getSalary()) {
                        BigestSalary = otherjobs.getSalary();
                    }
                }
            }
        }

        if(BigestSalary == -999999999) {
            System.out.println("There are no employees!");
        }
        else {
            System.out.println("Bigest Salary in world is: " + BigestSalary);
        }
    }

    public String getBigestSalaryinWorld() throws IOException {
        AuditWriterCSV.printAuditAction("printBigestSalaryinWorld");

        Integer BigestSalary = -999999999;
        for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    if(BigestSalary < teacher.getSalary()) {
                        BigestSalary = teacher.getSalary();
                    }
                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    if(BigestSalary < laborant.getSalary()) {
                        BigestSalary = laborant.getSalary();
                    }
                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    if(BigestSalary < secretaries.getSalary()) {
                        BigestSalary = secretaries.getSalary();
                    }
                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    if(BigestSalary < otherjobs.getSalary()) {
                        BigestSalary = otherjobs.getSalary();
                    }
                }
            }
        }

        if(BigestSalary == -999999999) {
            return "No employees";
        }
        else {
            return Integer.toString(BigestSalary);
            //System.out.println("Bigest Salary in world is: " + BigestSalary);
        }
    }

    public String getMedianSalaryInWorld() throws IOException {
        AuditWriterCSV.printAuditAction("printMedianSalaryInWorld");

        Integer SumSalary = 0;
        Integer NumSalary = 0;

        for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    NumSalary++;
                    SumSalary += teacher.getSalary();
                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    NumSalary++;
                    SumSalary += laborant.getSalary();
                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    NumSalary++;
                    SumSalary += secretaries.getSalary();
                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    NumSalary++;
                    SumSalary += otherjobs.getSalary();
                }
            }

        }

        if (NumSalary == 0) {
            return "No employees!";
            //System.out.println("There are no employees!");
        } else {
            return Integer.toString(SumSalary / NumSalary);
            //System.out.println("Median Salary in world is: " + SumSalary/NumSalary);
        }
    }

    public void printMedianSalaryInWorld() throws IOException {
        AuditWriterCSV.printAuditAction("printMedianSalaryInWorld");

        Integer SumSalary = 0;
        Integer NumSalary = 0;

        for (Country country : listOfCountries) {

            TreeSet<Faculty> listOfFaculties = country.getFaculties();
            for (Faculty faculty : listOfFaculties) {
                ArrayList<Teacher> listOfTeachers = faculty.getListOfTeachers();
                for (Teacher teacher : listOfTeachers) {
                    NumSalary++;
                    SumSalary += teacher.getSalary();
                }

                ArrayList<Laborant> listOfLaborants = faculty.getListOfLaborants();
                for (Laborant laborant : listOfLaborants) {
                    NumSalary++;
                    SumSalary += laborant.getSalary();
                }

                ArrayList<Secretary> listOfSecretaries = faculty.getListOfSecretaries();
                for (Secretary secretaries : listOfSecretaries) {
                    NumSalary++;
                    SumSalary += secretaries.getSalary();
                }

                ArrayList<OtherJobs> listOfOtherJobs = faculty.getListOfOtherJobs();
                for (OtherJobs otherjobs : listOfOtherJobs) {
                    NumSalary++;
                    SumSalary += otherjobs.getSalary();
                }
            }
        }

        if(NumSalary == 0) {
            System.out.println("There are no employees!");
        }
        else {
            System.out.println("Median Salary in world is: " + SumSalary/NumSalary);
        }
    }

}
