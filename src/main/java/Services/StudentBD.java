package Services;

import Entities.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentBD {
    public static StudentBD instance;
    public static int idx = 1;

    private StudentBD() {

    }

    public static StudentBD getInstance() {
        if (instance == null) {
            instance = new StudentBD();
        }
        return instance;
    }

    public void readStudent(World world) throws SQLException {
        String selectStatement = "SELECT * FROM student;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            String program = result.getString("program");
            Integer year = result.getInt("year");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            Student student = new Student(program,year,name,age);
            world.getCountry(countryName).getFaculty(facultyName).addStudent(student);
        }
    }

    public void actualizare_idx() throws SQLException {
        String selectStatement = "SELECT * FROM student;";
        PreparedStatement statement = BD.getInstance().getConnection().prepareStatement(selectStatement);
        ResultSet result = statement.executeQuery();

        while (result.next()) {

            Integer id = result.getInt("id");
            String name = result.getString("name");
            Integer age = result.getInt("age");
            String program = result.getString("program");
            Integer year = result.getInt("year");
            String facultyName = result.getString("faculty");
            String countryName = result.getString("country");

            if(idx <= id) {
                idx = id + 1;
            }
        }
    }

    public void updateStudent(World world, Student student, String facultyName, String countryName, String specificName, Integer index) throws SQLException {
        Student t = world.getCountry(countryName).getFaculty(facultyName).getStudent(specificName);
        if(t == null) {
            return;
        }

        world.getCountry(countryName).getFaculty(facultyName).getStudent(specificName).setPersonage(student.getPersonAge());
        world.getCountry(countryName).getFaculty(facultyName).getStudent(specificName).setProgram(student.getProgram());
        world.getCountry(countryName).getFaculty(facultyName).getStudent(specificName).setYear(student.getYear());
        world.getCountry(countryName).getFaculty(facultyName).getStudent(specificName).setPersonName(student.getPersonName());

        String updateStatement = "update student set name=?, age=?, program=?, year=?, faculty=?, country=? where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(updateStatement);

        stmt.setString(1,student.getPersonName());
        stmt.setInt(2,student.getPersonAge());
        stmt.setString(3,student.getProgram());
        stmt.setInt(4,student.getYear());
        stmt.setString(5,facultyName);
        stmt.setString(6,countryName);
        stmt.setInt(7,index);
        int result = stmt.executeUpdate();
    }

    public void addStudent(World world, Student student, String facultyName, String countryName) throws SQLException {
        actualizare_idx();
        System.out.println(idx);
        String insertStatement = "insert into student(id, name, age, program, year, faculty, country) VALUES (?, ?, ?, ?, ?, ?, ?)\n";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(insertStatement);

        stmt.setInt(1,idx);
        stmt.setString(2,student.getPersonName());
        stmt.setInt(3,student.getPersonAge());
        stmt.setString(4,student.getProgram());
        stmt.setInt(5,student.getYear());
        stmt.setString(6,facultyName);
        stmt.setString(7,countryName);
        int result = stmt.executeUpdate();


        /// ##############   aceste randuri sunt bonus
        if(world.getCountry(countryName) == null) {
            world.addCountry(new Country(countryName));
        }

        if(world.getCountry(countryName).getFaculty(facultyName) == null) {
            world.getCountry(countryName).addFaculty(new Faculty(facultyName));
        }
        /// ##############   aceste randuri sunt bonus

        world.getCountry(countryName).getFaculty(facultyName).addStudent(student);
    }

    public void deleteStudent(World world,Integer id, String nameStudent, String facultyName, String countryName) throws SQLException {
        String deleteStatement = "delete from student where id=?";
        PreparedStatement stmt = BD.getInstance().getConnection().prepareStatement(deleteStatement);
        stmt.setInt(1,id);
        if(world.getCountry(countryName) != null) {
            if(world.getCountry(countryName).getFaculty(facultyName) != null) {
                world.getCountry(countryName).getFaculty(facultyName).deleteStudent(nameStudent);
            }
        }
        int result = stmt.executeUpdate();
    }

    public void createStudent() throws SQLException {
        Statement stmt = BD.getInstance().getConnection().createStatement();
        String sql = "CREATE TABLE student " +
                "(id INTEGER, " +
                " name VARCHAR(255), " +
                " age INTEGER, " +
                " program VARCHAR(255), " +
                " year INTEGER, " +
                " faculty VARCHAR(255), " +
                " country VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        stmt.executeUpdate(sql);
    }
}
