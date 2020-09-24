package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class LaborantReadWriteCSV {
    private static LaborantReadWriteCSV instance = null;

    private LaborantReadWriteCSV() {

    }

    public static LaborantReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new LaborantReadWriteCSV();
        }
        return instance;
    }

    public void readLaborants(World world, String filePath) throws FileNotFoundException {
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
            String laboratorName = data[4];
            String doesHaveAnotherJob = data[5];
            String facultyName = data[6];
            String countryName = data[7];

            Laborant laborant = new Laborant(yearsOfExperience,salary,laboratorName,doesHaveAnotherJob,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addLaborant(laborant);
        }
    }

    public void writeLaborants(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("Name,Age,Years of experience,Salary,laboratorName,doesHaveAnotherJob,FacultyName,CountryName\n");

        for (Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                for(Laborant laborant : faculty.getListOfLaborants()) {
                    destination.write(laborant.getPersonName() + "," + laborant.getPersonAge() + "," + laborant.getYearsOfExperience() + "," + laborant.getSalary() + "," + laborant.getLaboratorName() + "," + laborant.getDoesHaveAnotherJob() + "," + faculty.getFacultyName() + "," + country.getCountryName() + "\n");
                }
            }
        }

        destination.close();
    }
}
