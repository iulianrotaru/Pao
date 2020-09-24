package Entities;

import java.util.TreeSet;
import Services.*;

public class Country {
    private String countryName;
    TreeSet<Faculty> Faculties;

    public Country(String countryName) {
        this.countryName = countryName;
        Faculties = new TreeSet<Faculty>(new FacultyComparator());
    }

    public String getCountryName() {
        return countryName;
    }

    public TreeSet<Faculty> getFaculties() {
        return this.Faculties;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void addFaculty(Faculty faculty) {
        Faculties.add(faculty);
    }

    public Faculty getFaculty(String FacultyName) {
        Faculty result = null;

        for (Faculty faculty : Faculties) {
            if (faculty.getFacultyName().equals(FacultyName)) {
                result = faculty;
                break;
            }
        }

        return result;
    }

}
