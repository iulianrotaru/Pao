package Services;

import Entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherBD {
    private static TeacherBD instance;
    public static int idx = 1;

    private TeacherBD() {

    }

    public static TeacherBD getInstance() {
        if (instance == null) {
            instance = new TeacherBD();
        }
        return instance;
    }

    public void readTeacher(World world) throws SQLException {
        String selectStatement = "SELECT * FROM teacher;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            Integer yearsOfExperience = result.getInt("yearsOfExperience");
            Integer salary = result.getInt("salary");
            String materieName = result.getString("materieName");
            String doesMeditate = result.getString("doesMeditate");
            String whenMeditate = result.getString("whenMeditate");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            Teacher teacher = new Teacher(yearsOfExperience,salary,materieName,doesMeditate,whenMeditate,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addTeacher(teacher);
        }
    }

    public void updateTeacher(World world, Teacher teacher, String facultyName, String countryName, String specificName, Integer index) throws SQLException {
        Teacher t = world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName);
        if(t == null) {
            return;
        }

        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setPersonage(teacher.getPersonAge());
        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setYearsOfExperience(teacher.getYearsOfExperience());
        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setSalary(teacher.getSalary());
        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setMaterieName(teacher.getMaterieName());
        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setDoesMeditate(teacher.getDoesMeditate());
        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setWhenMeditate(teacher.getWhenMeditate());
        world.getCountry(countryName).getFaculty(facultyName).getTeacher(specificName).setPersonName(teacher.getPersonName());

        String updateStatement = "update teacher set name=?, age=?, yearsOfExperience=?, salary=?, materieName=?, doesMeditate=?, whenMeditate=?, faculty=?, country=? where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(updateStatement);

        stmt.setString(1,teacher.getPersonName());
        stmt.setInt(2,teacher.getPersonAge());
        stmt.setInt(3,teacher.getYearsOfExperience());
        stmt.setInt(4,teacher.getSalary());
        stmt.setString(5,teacher.getMaterieName());
        stmt.setString(6,teacher.getDoesMeditate());
        stmt.setString(7,teacher.getWhenMeditate());
        stmt.setString(8,facultyName);
        stmt.setString(9,countryName);
        stmt.setInt(10,index);
        int result = stmt.executeUpdate();
    }

    public void actualizare_idx() throws SQLException {
        String selectStatement = "SELECT * FROM teacher;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            Integer yearsOfExperience = result.getInt("yearsOfExperience");
            Integer salary = result.getInt("salary");
            String materieName = result.getString("materieName");
            String doesMeditate = result.getString("doesMeditate");
            String whenMeditate = result.getString("whenMeditate");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            if(idx <= id) {
                idx = id + 1;
            }
        }
    }

    public void addTeacher(World world, Teacher teacher, String facultyName, String countryName) throws SQLException {
        actualizare_idx();
        System.out.println(idx);
        String insertStatement = "INSERT into teacher values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(insertStatement);

        stmt.setInt(1,idx);
        idx++;
        stmt.setString(2,teacher.getPersonName());
        stmt.setInt(3,teacher.getPersonAge());
        stmt.setInt(4,teacher.getYearsOfExperience());
        stmt.setInt(5,teacher.getSalary());
        stmt.setString(6,teacher.getMaterieName());
        stmt.setString(7,teacher.getDoesMeditate());
        stmt.setString(8,teacher.getWhenMeditate());
        stmt.setString(9,facultyName);
        stmt.setString(10,countryName);
        int result = stmt.executeUpdate();

        /// ##############   aceste randuri sunt bonus
        if(world.getCountry(countryName) == null) {
            world.addCountry(new Country(countryName));
        }

        if(world.getCountry(countryName).getFaculty(facultyName) == null) {
            world.getCountry(countryName).addFaculty(new Faculty(facultyName));
        }
        /// ##############   aceste randuri sunt bonus

        world.getCountry(countryName).getFaculty(facultyName).addTeacher(teacher);
    }

    public void deleteTeacher(World world,Integer id, String nameTeacher, String facultyName, String countryName) throws SQLException {
        String deleteStatement = "delete from teacher where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(deleteStatement);
        stmt.setInt(1,id);
        if(world.getCountry(countryName) != null) {
            if(world.getCountry(countryName).getFaculty(facultyName) != null) {
                world.getCountry(countryName).getFaculty(facultyName).deleteStudent(nameTeacher);
            }
        }
        int result = stmt.executeUpdate();
    }

    public void createTeacher() throws SQLException {
        Statement stmt = BD.getInstance().getConnection().createStatement();
        String sql = "CREATE TABLE TEACHER " +
                "(id INTEGER, " +
                " name VARCHAR(255), " +
                " age INTEGER, " +
                " yearsOfExperience INTEGER, " +
                " salary INTEGER, " +
                " materieName VARCHAR(255), " +
                " doesMeditate VARCHAR(255), " +
                " whenMeditate VARCHAR(255), " +
                " faculty VARCHAR(255), " +
                " country VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}
