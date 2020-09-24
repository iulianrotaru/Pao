package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class TeacherReadWriteCSV {
    private static TeacherReadWriteCSV instance = null;

    private TeacherReadWriteCSV() {

    }

    public static TeacherReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new TeacherReadWriteCSV();
        }
        return instance;
    }

    public void readTeachers(World world, String filePath) throws FileNotFoundException {
        File fileSource = new File(filePath);
        Scanner scanner = new Scanner(fileSource);

        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] data = line.split(",");

            String name = data[0];
            Integer age = Integer.parseInt(data[1]);
            Integer yearsOfExperience = Integer.parseInt(data[2]);
            Integer salary = Integer.parseInt(data[3]);
            String materieName = data[4];
            String doesMeditate = data[5];
            String whenMeditate = data[6];
            String facultyName = data[7];
            String countryName = data[8];

            Teacher teacher = new Teacher(yearsOfExperience,salary,materieName,doesMeditate,whenMeditate,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addTeacher(teacher);
        }
    }

    public void writeTeachers(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("Name,Age,Years of experience,Salary,MaterieName,DoesMeditate,WhenMeditate,FacultyName,CountryName\n");

        for (Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                for(Teacher teacher : faculty.getListOfTeachers()) {
                    destination.write(teacher.getPersonName() + "," + teacher.getPersonAge() + "," + teacher.getYearsOfExperience() + "," + teacher.getSalary() + "," + teacher.getMaterieName() + "," + teacher.getDoesMeditate() + "," + teacher.getWhenMeditate() + "," + faculty.getFacultyName() + "," + country.getCountryName() + "\n");
                }
            }
        }

        destination.close();
    }
}
