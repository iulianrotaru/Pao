package Services;

import Entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SecretaryBD {
    private static SecretaryBD instance;
    public static int idx = 1;

    private SecretaryBD() {

    }

    public static SecretaryBD getInstance() {
        if (instance == null) {
            instance = new SecretaryBD();
        }
        return instance;
    }

    public void readSecretary(World world) throws SQLException {
        String selectStatement = "SELECT * FROM secretary;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            Integer yearsOfExperience = result.getInt("yearsOfExperience");
            Integer salary = result.getInt("salary");
            String program = result.getString("program");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            Secretary secretary = new Secretary(yearsOfExperience,salary,program,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addSecretary(secretary);
        }
    }

    public void updateSecretary(World world, Secretary secretary, String facultyName, String countryName, String specificName, Integer index) throws SQLException {
        Secretary t = world.getCountry(countryName).getFaculty(facultyName).getSecretary(specificName);
        if(t == null) {
            return;
        }

        world.getCountry(countryName).getFaculty(facultyName).getSecretary(specificName).setPersonage(secretary.getPersonAge());
        world.getCountry(countryName).getFaculty(facultyName).getSecretary(specificName).setYearsOfExperience(secretary.getYearsOfExperience());
        world.getCountry(countryName).getFaculty(facultyName).getSecretary(specificName).setSalary(secretary.getSalary());
        world.getCountry(countryName).getFaculty(facultyName).getSecretary(specificName).setProgram(secretary.getProgram());
        world.getCountry(countryName).getFaculty(facultyName).getSecretary(specificName).setPersonName(secretary.getPersonName());

        String updateStatement = "update secretary set name=?, age=?, yearsOfExperience=?, salary=?, program=?, faculty=?, country=? where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(updateStatement);

        stmt.setString(1,secretary.getPersonName());
        stmt.setInt(2,secretary.getPersonAge());
        stmt.setInt(3,secretary.getYearsOfExperience());
        stmt.setInt(4,secretary.getSalary());
        stmt.setString(5,secretary.getProgram());
        stmt.setString(6,facultyName);
        stmt.setString(7,countryName);
        stmt.setInt(8,index);
        int result = stmt.executeUpdate();
    }

    public void addSecretary(World world, Secretary secretary, String facultyName, String countryName) throws SQLException {
        String insertStatement = "INSERT into secretary values (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(insertStatement);

        stmt.setInt(1,idx);
        idx++;
        stmt.setString(2,secretary.getPersonName());
        stmt.setInt(3,secretary.getPersonAge());
        stmt.setInt(4,secretary.getYearsOfExperience());
        stmt.setInt(5,secretary.getSalary());
        stmt.setString(6,secretary.getProgram());
        stmt.setString(7,facultyName);
        stmt.setString(8,countryName);
        int result = stmt.executeUpdate();

        world.getCountry(countryName).getFaculty(facultyName).addSecretary(secretary);
    }

    public void deleteSecretary(World world,Integer id, String nameSecretary, String facultyName, String countryName) throws SQLException {
        String deleteStatement = "delete from secretary where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(deleteStatement);
        stmt.setInt(1,id);
        world.getCountry(countryName).getFaculty(facultyName).deleteSecretary(nameSecretary);
        int result = stmt.executeUpdate();
    }

    public void createSecretary() throws SQLException {
        Statement stmt = BD.getInstance().getConnection().createStatement();
        String sql = "CREATE TABLE Secretary " +
                "(id INTEGER, " +
                " name VARCHAR(255), " +
                " age INTEGER, " +
                " yearsOfExperience INTEGER, " +
                " salary INTEGER, " +
                " program VARCHAR(255), " +
                " faculty VARCHAR(255), " +
                " country VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}
