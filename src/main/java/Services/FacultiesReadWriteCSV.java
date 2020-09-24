package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class FacultiesReadWriteCSV {
    private static FacultiesReadWriteCSV instance = null;

    private FacultiesReadWriteCSV() {

    }

    public static FacultiesReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new FacultiesReadWriteCSV();
        }
        return instance;
    }

    public void readFaculties(World world, String filePath) throws FileNotFoundException {
        File fileSource = new File(filePath);
        Scanner scanner = new Scanner(fileSource);

        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] data = line.split(",");

            world.getCountry(data[1]).addFaculty(new Faculty(data[0]));
        }
    }

    public void writeFaculties(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("FacultyName,CountryName\n");

        for (Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                destination.write(faculty.getFacultyName() + "," + country.getCountryName() + "\n");
            }
        }

        destination.close();
    }
}
