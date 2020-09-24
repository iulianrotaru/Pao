package Services;

import Entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LaborantBD {
    private static LaborantBD instance;
    public static int idx = 1;

    private LaborantBD() {

    }

    public static LaborantBD getInstance() {
        if (instance == null) {
            instance = new LaborantBD();
        }
        return instance;
    }

    public void readLaborant(World world) throws SQLException {
        String selectStatement = "SELECT * FROM laborant;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            Integer yearsOfExperience = result.getInt("yearsOfExperience");
            Integer salary = result.getInt("salary");
            String laboratorName = result.getString("laboratorName");
            String doesHaveAnotherJob = result.getString("doesHaveAnotherJob");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            Laborant laborant = new Laborant(yearsOfExperience,salary,laboratorName,doesHaveAnotherJob,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addLaborant(laborant);
        }
    }

    public void updateLaborant(World world, Laborant laborant, String facultyName, String countryName, String specificName, Integer index) throws SQLException {
        Laborant t = world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName);
        if(t == null) {
            return;
        }

        world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName).setPersonage(laborant.getPersonAge());
        world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName).setYearsOfExperience(laborant.getYearsOfExperience());
        world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName).setSalary(laborant.getSalary());
        world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName).setLaboratorName(laborant.getLaboratorName());
        world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName).setDoesHaveAnotherJob(laborant.getDoesHaveAnotherJob());
        world.getCountry(countryName).getFaculty(facultyName).getLaborant(specificName).setPersonName(laborant.getPersonName());

        String updateStatement = "update laborant set name=?, age=?, yearsOfExperience=?, salary=?, laboratorName=?, doesHaveAnotherJob=?, faculty=?, country=? where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(updateStatement);

        stmt.setString(1,laborant.getPersonName());
        stmt.setInt(2,laborant.getPersonAge());
        stmt.setInt(3,laborant.getYearsOfExperience());
        stmt.setInt(4,laborant.getSalary());
        stmt.setString(5,laborant.getLaboratorName());
        stmt.setString(6,laborant.getDoesHaveAnotherJob());
        stmt.setString(7,facultyName);
        stmt.setString(8,countryName);
        stmt.setInt(9,index);
        int result = stmt.executeUpdate();
    }

    public void addLaborant(World world, Laborant laborant, String facultyName, String countryName) throws SQLException {
        String insertStatement = "INSERT into laborant values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(insertStatement);

        stmt.setInt(1,idx);
        idx++;
        stmt.setString(2,laborant.getPersonName());
        stmt.setInt(3,laborant.getPersonAge());
        stmt.setInt(4,laborant.getYearsOfExperience());
        stmt.setInt(5,laborant.getSalary());
        stmt.setString(6,laborant.getLaboratorName());
        stmt.setString(7,laborant.getDoesHaveAnotherJob());
        stmt.setString(8,facultyName);
        stmt.setString(9,countryName);
        int result = stmt.executeUpdate();

        world.getCountry(countryName).getFaculty(facultyName).addLaborant(laborant);
    }

    public void deleteLaborant(World world,Integer id, String nameLaborant, String facultyName, String countryName) throws SQLException {
        String deleteStatement = "delete from laborant where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(deleteStatement);
        stmt.setInt(1,id);
        world.getCountry(countryName).getFaculty(facultyName).deleteLaborant(nameLaborant);
        int result = stmt.executeUpdate();
    }

    public void createLaborant() throws SQLException {
        Statement stmt = BD.getInstance().getConnection().createStatement();
        String sql = "CREATE TABLE Laborant " +
                "(id INTEGER, " +
                " name VARCHAR(255), " +
                " age INTEGER, " +
                " yearsOfExperience INTEGER, " +
                " salary INTEGER, " +
                " laboratorName VARCHAR(255), " +
                " doesHaveAnotherJob VARCHAR(255), " +
                " faculty VARCHAR(255), " +
                " country VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}
