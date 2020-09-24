package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class OtherJobsReadWriteCSV {

    private static OtherJobsReadWriteCSV instance = null;

    private OtherJobsReadWriteCSV() {

    }

    public static OtherJobsReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new OtherJobsReadWriteCSV();
        }
        return instance;
    }

    public void readOtherJobs(World world, String filePath) throws FileNotFoundException {
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
            String professionName = data[4];
            String facultyName = data[5];
            String countryName = data[6];

            OtherJobs otherJobs = new OtherJobs(yearsOfExperience,salary,professionName,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addOtherJobs(otherJobs);
        }
    }

    public void writeOtherJobs(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("Name,Age,Years of experience,Salary,professionName,FacultyName,CountryName\n");

        for (Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                for(OtherJobs otherJobs : faculty.getListOfOtherJobs()) {
                    destination.write(otherJobs.getPersonName() + "," + otherJobs.getPersonAge() + "," + otherJobs.getYearsOfExperience() + "," + otherJobs.getSalary() + "," + otherJobs.getProfessionName() + "," + faculty.getFacultyName() + "," + country.getCountryName() + "\n");
                }
            }
        }

        destination.close();
    }
}
