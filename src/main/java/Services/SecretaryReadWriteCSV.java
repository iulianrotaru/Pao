package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class SecretaryReadWriteCSV {
    private static SecretaryReadWriteCSV instance = null;

    private SecretaryReadWriteCSV() {

    }

    public static SecretaryReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new SecretaryReadWriteCSV();
        }
        return instance;
    }

    public void readSecretaries(World world, String filePath) throws FileNotFoundException {
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
            String program = data[4];
            String facultyName = data[5];
            String countryName = data[6];

            Secretary secretary = new Secretary(yearsOfExperience,salary,program,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addSecretary(secretary);
        }
    }

    public void writeSecretaries(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("Name,Age,Years of experience,Salary,program,FacultyName,CountryName\n");

        for (Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                for(Secretary secretary : faculty.getListOfSecretaries()) {
                    destination.write(secretary.getPersonName() + "," + secretary.getPersonAge() + "," + secretary.getYearsOfExperience() + "," + secretary.getSalary() + "," + secretary.getProgram() + "," + faculty.getFacultyName() + "," + country.getCountryName() + "\n");
                }
            }
        }

        destination.close();
    }
}
