package Services;

import Entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OtherJobsBD {
    private static OtherJobsBD instance;
    public static int idx = 1;

    private OtherJobsBD() {

    }

    public static OtherJobsBD getInstance() {
        if (instance == null) {
            instance = new OtherJobsBD();
        }
        return instance;
    }

    public void readOtherJobs(World world) throws SQLException {
        String selectStatement = "SELECT * FROM otherJobs;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            Integer yearsOfExperience = result.getInt("yearsOfExperience");
            Integer salary = result.getInt("salary");
            String professionName = result.getString("professionName");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            OtherJobs otherJobs = new OtherJobs(yearsOfExperience,salary,professionName,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addOtherJobs(otherJobs);
        }
    }

    public void updateOtherJobs(World world, OtherJobs otherJobs, String facultyName, String countryName, String specificName, Integer index) throws SQLException {
        OtherJobs t = world.getCountry(countryName).getFaculty(facultyName).getOtherJobs(specificName);
        if(t == null) {
            return;
        }

        world.getCountry(countryName).getFaculty(facultyName).getOtherJobs(specificName).setPersonage(otherJobs.getPersonAge());
        world.getCountry(countryName).getFaculty(facultyName).getOtherJobs(specificName).setYearsOfExperience(otherJobs.getYearsOfExperience());
        world.getCountry(countryName).getFaculty(facultyName).getOtherJobs(specificName).setSalary(otherJobs.getSalary());
        world.getCountry(countryName).getFaculty(facultyName).getOtherJobs(specificName).setProfessionName(otherJobs.getProfessionName());
        world.getCountry(countryName).getFaculty(facultyName).getOtherJobs(specificName).setPersonName(otherJobs.getPersonName());

        String updateStatement = "update otherJobs set name=?, age=?, yearsOfExperience=?, salary=?, professionName=?, faculty=?, country=? where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(updateStatement);

        stmt.setString(1,otherJobs.getPersonName());
        stmt.setInt(2,otherJobs.getPersonAge());
        stmt.setInt(3,otherJobs.getYearsOfExperience());
        stmt.setInt(4,otherJobs.getSalary());
        stmt.setString(5,otherJobs.getProfessionName());
        stmt.setString(6,facultyName);
        stmt.setString(7,countryName);
        stmt.setInt(8,index);
        int result = stmt.executeUpdate();
    }

    public void addOtherJobs(World world, OtherJobs otherJobs, String facultyName, String countryName) throws SQLException {
        String insertStatement = "INSERT into otherJobs values (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(insertStatement);

        stmt.setInt(1,idx);
        idx++;
        stmt.setString(2,otherJobs.getPersonName());
        stmt.setInt(3,otherJobs.getPersonAge());
        stmt.setInt(4,otherJobs.getYearsOfExperience());
        stmt.setInt(5,otherJobs.getSalary());
        stmt.setString(6,otherJobs.getProfessionName());
        stmt.setString(7,facultyName);
        stmt.setString(8,countryName);
        int result = stmt.executeUpdate();

        world.getCountry(countryName).getFaculty(facultyName).addOtherJobs(otherJobs);
    }

    public void deleteOtherJobs(World world,Integer id, String nameOtherJobs, String facultyName, String countryName) throws SQLException {
        String deleteStatement = "delete from otherJobs where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(deleteStatement);
        stmt.setInt(1,id);
        world.getCountry(countryName).getFaculty(facultyName).deleteOtherJobs(nameOtherJobs);
        int result = stmt.executeUpdate();
    }

    public void createOtherJobs() throws SQLException {
        Statement stmt = BD.getInstance().getConnection().createStatement();
        String sql = "CREATE TABLE otherJobs " +
                "(id INTEGER, " +
                " name VARCHAR(255), " +
                " age INTEGER, " +
                " yearsOfExperience INTEGER, " +
                " salary INTEGER, " +
                " professionName VARCHAR(255), " +
                " faculty VARCHAR(255), " +
                " country VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}
