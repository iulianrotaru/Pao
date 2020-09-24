package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class StudentReadWriteCSV {

    private static StudentReadWriteCSV instance = null;

    private StudentReadWriteCSV() {

    }

    public static StudentReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new StudentReadWriteCSV();
        }
        return instance;
    }

    public void readStudents(World world, String filePath) throws FileNotFoundException {
        File fileSource = new File(filePath);
        Scanner scanner = new Scanner(fileSource);

        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] data = line.split(",");

            String name = data[0];
            Integer age = Integer.parseInt(data[1]);
            String program = data[2];
            Integer year = Integer.parseInt(data[3]);
            String facultyName = data[4];
            String countryName = data[5];

            Student student = new Student(program,year,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addStudent(student);
        }
    }

    public void writeStudents(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("Name,Age,program,year,FacultyName,CountryName\n");

        for (Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                for(Student student : faculty.getListOfStudents()) {
                    destination.write(student.getPersonName() + "," + student.getPersonAge() + "," + student.getProgram() + "," + student.getYear() + "," + faculty.getFacultyName() + "," + country.getCountryName() + "\n");
                }
            }
        }

        destination.close();
    }
}
