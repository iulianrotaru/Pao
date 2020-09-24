package Services;

import java.io.*;
import java.util.Scanner;
import Entities.*;

public class CountryReadWriteCSV {
    private static CountryReadWriteCSV instance = null;

    private CountryReadWriteCSV() {

    }

    public static CountryReadWriteCSV getInstance() {
        if (instance == null) {
            instance = new CountryReadWriteCSV();
        }
        return instance;
    }

    public void readCountry(World world, String filePath) throws FileNotFoundException {
        File fileSource = new File(filePath);
        Scanner scanner = new Scanner(fileSource);

        scanner.nextLine();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] data = line.split(",");

            world.addCountry(new Country(data[0]));
        }
    }

    public void writeCountry(World world, String filePath) throws IOException {
        FileWriter destination = new FileWriter(filePath);
        destination.write("CountryName\n");

        for (Country country : world.getListOfCountries()) {
            destination.write(country.getCountryName() + "\n");
        }

        destination.close();
    }
}
