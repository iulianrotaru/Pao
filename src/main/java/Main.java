import Entities.*;
import Services.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    private Button buttonStudent;
    private Button buttonTeacher;
    public static World Wworld = new World();

    private void StudentView(Stage primaryStage) throws NumberFormatException {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);

        Label idLabel = new Label("Id:     ");
        TextField idTextField = new TextField ();
        HBox idHbox = new HBox(idLabel, idTextField);
        idHbox.setSpacing(68);
        GridPane.setConstraints(idHbox, 0, 0);
        grid.getChildren().add(idHbox);

        Label nameLabel = new Label("Name:   ");
        TextField nameTextField = new TextField ();
        HBox nameHbox = new HBox(nameLabel, nameTextField);
        nameHbox.setSpacing(50);
        GridPane.setConstraints(nameHbox, 0, 1);
        grid.getChildren().add(nameHbox);


        Label ageLabel = new Label("Age:    ");
        TextField ageTextField = new TextField ();
        HBox ageHbox = new HBox(ageLabel, ageTextField);
        ageHbox.setSpacing(58);
        GridPane.setConstraints(ageHbox, 0, 2);
        grid.getChildren().add(ageHbox);


        Label programLabel = new Label("Program:");
        TextField programTextField = new TextField ();
        HBox programHbox = new HBox(programLabel, programTextField);
        programHbox.setSpacing(44);
        GridPane.setConstraints(programHbox, 0, 3);
        grid.getChildren().add(programHbox);



        Label yearLabel = new Label("Year:   ");
        TextField yearTextField = new TextField ();
        HBox yearHbox = new HBox(yearLabel, yearTextField);
        yearHbox.setSpacing(61);
        GridPane.setConstraints(yearHbox, 0, 4);
        grid.getChildren().add(yearHbox);

        Label facultyLabel = new Label("Faculty:");
        TextField facultyTextField = new TextField();
        HBox facultyHbox = new HBox(facultyLabel, facultyTextField);
        facultyHbox.setSpacing(55);
        GridPane.setConstraints(facultyHbox, 0, 5);
        grid.getChildren().add(facultyHbox);

        Label countryLabel = new Label("Country:");
        TextField countryTextField = new TextField();
        HBox countryHbox = new HBox(countryLabel, countryTextField);
        countryHbox.setSpacing(50);
        GridPane.setConstraints(countryHbox, 0, 6);
        grid.getChildren().add(countryHbox);

        Button deleteButton = new Button("DELETE");
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                int ok = 1;

                if(nameTextField.getText().equals("") || idTextField.getText().equals("")) {
                    ok = 0;
                }

                if(ok == 1) {
                    String name = nameTextField.getText();
                    Integer id;
                    try {
                        id = Integer.parseInt(idTextField.getText());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    String country = countryTextField.getText();
                    String faculty = facultyTextField.getText();

                    nameTextField.setText("");
                    ageTextField.setText("");
                    yearTextField.setText("");
                    countryTextField.setText("");
                    facultyTextField.setText("");
                    programTextField.setText("");
                    idTextField.setText("");

                    StudentBD studentBD = StudentBD.getInstance();
                    ///World Wworld = new World();
                    try {
                        studentBD.deleteStudent(Wworld,id,name,faculty,country);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(ok == 0)
                {
                    if(nameTextField.getText().equals("")) nameTextField.setText("?");
                    if(ageTextField.getText().equals("")) ageTextField.setText("?");
                    if(yearTextField.getText().equals("")) yearTextField.setText("?");
                    if(countryTextField.getText().equals("")) countryTextField.setText("?");
                    if(facultyTextField.getText().equals("")) facultyTextField.setText("?");
                    if(programTextField.getText().equals("")) programTextField.setText("?");
                    idTextField.setText("-");
                }
            }
        });

        Button addButton = new Button("ADD STUDENT");
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String name,country,faculty,program;
                int age,year,id;

                int ok = 1;

                if(nameTextField.getText().equals("") ||
                        ageTextField.getText().equals("") ||
                        yearTextField.getText().equals("") ||
                        countryTextField.getText().equals("") ||
                        facultyTextField.getText().equals("") ||
                        programTextField.getText().equals("")) {
                    ok = 0;
                }

                if(ok == 1) {
                    name = nameTextField.getText();
                    try {
                        age = Integer.parseInt(ageTextField.getText());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    try {
                        year = Integer.parseInt(yearTextField.getText());
                    } catch (NumberFormatException e) {
                        return ;
                    }
                    country = countryTextField.getText();
                    faculty = facultyTextField.getText();
                    program = programTextField.getText();

                    nameTextField.setText("");
                    ageTextField.setText("");
                    yearTextField.setText("");
                    countryTextField.setText("");
                    facultyTextField.setText("");
                    programTextField.setText("");
                    idTextField.setText("");

                    Student addStudent = new Student(program,year,name,age);

                    StudentBD studentBD = StudentBD.getInstance();
                    //World Wworld = new World();
                    try {
                        studentBD.addStudent(Wworld,addStudent,faculty,country);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if(ok == 0)
                {
                    if(nameTextField.getText().equals("")) nameTextField.setText("?");
                    if(ageTextField.getText().equals("")) ageTextField.setText("?");
                    if(yearTextField.getText().equals("")) yearTextField.setText("?");
                    if(countryTextField.getText().equals("")) countryTextField.setText("?");
                    if(facultyTextField.getText().equals("")) facultyTextField.setText("?");
                    if(programTextField.getText().equals("")) programTextField.setText("?");
                    idTextField.setText("-");
                }
            }
        });

        Button backButton = new Button("BACK");
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StartApp(primaryStage);
            }
        });

        HBox butHbox = new HBox(addButton,deleteButton,backButton);
        butHbox.setSpacing(25);
        GridPane.setConstraints(butHbox, 0, 7);
        grid.getChildren().add(butHbox);

        Button CountryExist = new Button("Country exist?");

        TextField CountryExistTextField = new TextField ();
        HBox CountryExistHbox = new HBox(CountryExist, CountryExistTextField);
        CountryExistHbox.setSpacing(10);
        GridPane.setConstraints(CountryExistHbox, 0, 9);
        grid.getChildren().add(CountryExistHbox);

        CountryExist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String country = countryTextField.getText();
                    countryTextField.setText("");
                    CountryExistTextField.setText(Wworld.ThisCountryExist(country));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        Button FacultyExist = new Button("Faculty exist?");

        TextField FacultyExistTextField = new TextField ();
        HBox FacultyExistHbox = new HBox(FacultyExist, FacultyExistTextField);
        FacultyExistHbox.setSpacing(10);
        GridPane.setConstraints(FacultyExistHbox, 0, 8);
        grid.getChildren().add(FacultyExistHbox);

        FacultyExist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    String faculty = facultyTextField.getText();
                    facultyTextField.setText("");
                    FacultyExistTextField.setText(Wworld.ThisFacultyExist(faculty));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        HBox hbox = new HBox(grid);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void TeacherView(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);

        Label idLabel = new Label("Id:     ");
        TextField idTextField = new TextField();
        HBox idHbox = new HBox(idLabel, idTextField);
        idHbox.setSpacing(120);
        GridPane.setConstraints(idHbox, 0, 0);
        grid.getChildren().add(idHbox);

        Label countryLabel = new Label("Country:");
        TextField countryTextField = new TextField();
        HBox countryHbox = new HBox(countryLabel, countryTextField);
        countryHbox.setSpacing(100);
        GridPane.setConstraints(countryHbox, 0, 9);
        grid.getChildren().add(countryHbox);

        Label facultyLabel = new Label("Faculty:");
        TextField facultyTextField = new TextField();
        HBox facultyHbox = new HBox(facultyLabel, facultyTextField);
        facultyHbox.setSpacing(105);
        GridPane.setConstraints(facultyHbox, 0, 8);
        grid.getChildren().add(facultyHbox);

        Label nameLabel = new Label("Name:   ");
        TextField nameTextField = new TextField ();
        HBox nameHbox = new HBox(nameLabel, nameTextField);
        nameHbox.setSpacing(100);
        GridPane.setConstraints(nameHbox, 0, 1);
        grid.getChildren().add(nameHbox);


        Label ageLabel = new Label("Age:    ");
        TextField ageTextField = new TextField ();
        HBox ageHbox = new HBox(ageLabel, ageTextField);
        ageHbox.setSpacing(109);
        GridPane.setConstraints(ageHbox, 0, 2);
        grid.getChildren().add(ageHbox);


        Label ExperienceLabel = new Label("Experience:");
        TextField ExperienceTextField = new TextField ();
        HBox ExperienceHbox = new HBox(ExperienceLabel, ExperienceTextField);
        ExperienceHbox.setSpacing(81);
        GridPane.setConstraints(ExperienceHbox, 0, 3);
        grid.getChildren().add(ExperienceHbox);

        Label salaryLabel = new Label("Salary:");
        TextField salaryTextField = new TextField ();
        HBox salaryHbox = new HBox(salaryLabel, salaryTextField);
        salaryHbox.setSpacing(113);
        GridPane.setConstraints(salaryHbox, 0, 4);
        grid.getChildren().add(salaryHbox);

        Label MaterieLabel = new Label("Materie Name");
        TextField MaterieTextField = new TextField ();
        HBox MaterieHbox = new HBox(MaterieLabel, MaterieTextField);
        MaterieHbox.setSpacing(61);
        GridPane.setConstraints(MaterieHbox, 0, 5);
        grid.getChildren().add(MaterieHbox);

        Label doesMeditateLabel = new Label("Does Meditate:");
        TextField doesMeditateTextField = new TextField ();
        HBox doesMeditateHbox = new HBox(doesMeditateLabel, doesMeditateTextField);
        doesMeditateHbox.setSpacing(55);
        GridPane.setConstraints(doesMeditateHbox, 0, 6);
        grid.getChildren().add(doesMeditateHbox);

        Label whenMeditateLabel = new Label("When Meditate:");
        TextField whenMeditateTextField = new TextField ();
        HBox whenMeditateHbox = new HBox(whenMeditateLabel, whenMeditateTextField);
        whenMeditateHbox.setSpacing(50);
        GridPane.setConstraints(whenMeditateHbox, 0, 7);
        grid.getChildren().add(whenMeditateHbox);

        Button deleteButton = new Button("DELETE");
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                int ok = 1;

                if(nameTextField.getText().equals("") || idTextField.getText().equals("")) {
                    ok = 0;
                }

                if(ok == 1) {
                    String name = nameTextField.getText();
                    Integer id;
                    try {
                        id = Integer.parseInt(idTextField.getText());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    String country = countryTextField.getText();
                    String faculty = facultyTextField.getText();

                    nameTextField.setText("");
                    ageTextField.setText("");
                    countryTextField.setText("");
                    facultyTextField.setText("");
                    salaryTextField.setText("");
                    ExperienceTextField.setText("");
                    idTextField.setText("");
                    whenMeditateTextField.setText("");
                    doesMeditateTextField.setText("");
                    MaterieTextField.setText("");

                    TeacherBD teacherBD = TeacherBD.getInstance();
                    ///World Wworld = new World();
                    try {
                        teacherBD.deleteTeacher(Wworld,id,name,faculty,country);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        Button addButton = new Button("ADD TEACHER");
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String name,country,faculty,whenMeditate,doesMeditate,materieName;
                int age,id,salary,yearsOfExperience;

                int ok = 1;

                if(ok == 1) {
                    name = nameTextField.getText();
                    try {
                        age = Integer.parseInt(ageTextField.getText());
                    } catch (NumberFormatException e) {
                        return;
                    }
                    try {
                        yearsOfExperience = Integer.parseInt(ExperienceTextField.getText());
                    } catch (NumberFormatException e) {
                        return ;
                    }
                    try {
                        salary = Integer.parseInt(salaryTextField.getText());
                    } catch (NumberFormatException e) {
                        return ;
                    }
                    country = countryTextField.getText();
                    faculty = facultyTextField.getText();
                    whenMeditate = whenMeditateTextField.getText();
                    doesMeditate = doesMeditateTextField.getText();
                    materieName = MaterieTextField.getText();


                    nameTextField.setText("");
                    ageTextField.setText("");
                    countryTextField.setText("");
                    facultyTextField.setText("");
                    salaryTextField.setText("");
                    ExperienceTextField.setText("");
                    idTextField.setText("");
                    whenMeditateTextField.setText("");
                    doesMeditateTextField.setText("");
                    MaterieTextField.setText("");

                    Teacher addTeacher = new Teacher(yearsOfExperience,salary,materieName,doesMeditate,whenMeditate,name,age);

                    TeacherBD teacherBD = TeacherBD.getInstance();
                    ////World Wworld = new World();
                    try {
                        teacherBD.addTeacher(Wworld,addTeacher,faculty,country);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        Button backButton = new Button("BACK");
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StartApp(primaryStage);
            }
        });

        HBox butHbox = new HBox(addButton,deleteButton,backButton);
        butHbox.setSpacing(51);
        GridPane.setConstraints(butHbox, 0, 10);
        grid.getChildren().add(butHbox);

        Button salariuMinim = new Button("Salariu Minim");

        TextField salariuMinimTextField = new TextField ();
        HBox salariuMinimHbox = new HBox(salariuMinim, salariuMinimTextField);
        salariuMinimHbox.setSpacing(50);
        GridPane.setConstraints(salariuMinimHbox, 0, 11);
        grid.getChildren().add(salariuMinimHbox);

        salariuMinim.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    salariuMinimTextField.setText(Wworld.getLowestSalaryinWorld());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        Button salariuMediu = new Button("Salariu Mediu");

        TextField salariuMediuTextField = new TextField ();
        HBox salariuMediuHbox = new HBox(salariuMediu, salariuMediuTextField);
        salariuMediuHbox.setSpacing(50);
        GridPane.setConstraints(salariuMediuHbox, 0, 12);
        grid.getChildren().add(salariuMediuHbox);

        salariuMediu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    salariuMediuTextField.setText(Wworld.getMedianSalaryInWorld());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        Button salariuMaxim = new Button("Salariu Maxim");

        TextField salariuMaximTextField = new TextField ();
        HBox salariuMaximHbox = new HBox(salariuMaxim, salariuMaximTextField);
        salariuMaximHbox.setSpacing(50);
        GridPane.setConstraints(salariuMaximHbox, 0, 13);
        grid.getChildren().add(salariuMaximHbox);

        salariuMaxim.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    salariuMaximTextField.setText(Wworld.getBigestSalaryinWorld());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });


        HBox hbox = new HBox(grid);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void StartApp(Stage primaryStage) {
        primaryStage.setTitle("Administratie Facultate");

        buttonStudent = new Button("Student");
        buttonTeacher = new Button("Teacher");

        buttonStudent.setOnAction(value ->  {
            StudentView(primaryStage);
        });

        buttonTeacher.setOnAction(value ->  {
            TeacherView(primaryStage);
        });

        HBox hbox = new HBox(buttonStudent, buttonTeacher);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(hbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        StartApp(primaryStage);

    }

    public static void main(String[] args) throws SQLException, IOException {

        //World world = new World();
        AuditThread auditThread = AuditThread.getInstance();

        CountryReadWriteCSV countryCSV = CountryReadWriteCSV.getInstance();
        FacultiesReadWriteCSV facultyCSV = FacultiesReadWriteCSV.getInstance();
        TeacherReadWriteCSV teacherCSV = TeacherReadWriteCSV.getInstance();
        LaborantReadWriteCSV laborantCSV = LaborantReadWriteCSV.getInstance();
        SecretaryReadWriteCSV secretaryCSV = SecretaryReadWriteCSV.getInstance();
        StudentReadWriteCSV studentCSV = StudentReadWriteCSV.getInstance();
        OtherJobsReadWriteCSV otherJobsCSV = OtherJobsReadWriteCSV.getInstance();

        countryCSV.readCountry(Wworld,"data/country.csv");
        facultyCSV.readFaculties(Wworld,"data/faculties.csv");
        teacherCSV.readTeachers(Wworld,"data/teachers.csv");
        laborantCSV.readLaborants(Wworld,"data/laborants.csv");
        secretaryCSV.readSecretaries(Wworld,"data/secretaries.csv");
        studentCSV.readStudents(Wworld,"data/students.csv");
        otherJobsCSV.readOtherJobs(Wworld,"data/otherJobs.csv");
        

        ///*
        Thread threadTeacher = new Thread() {
            @Override
            public void run() {
                String name = getName();

                try {
                    Wworld.printMedianSalaryInWorld();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printMedianSalaryInWworld",name);

                try {
                    Wworld.printLowestSalaryinWorld();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printLowestSalaryinWorld",name);

                try {
                    Wworld.printBigestSalaryinWorld();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printBigestSalaryinWorld",name);
            }
        };

        Thread threadStudent = new Thread() {
            @Override
            public void run() {
                String name = getName();

                try {
                    System.out.println(Wworld.getCountry("USA").getFaculty("BOBY").getNumberOfEmployees());
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("getNumberOfEmployees",name);

                try {
                    System.out.println(Wworld.getCountry("USA").getFaculty("BOBY").getNumberOfPersons());
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("getNumberOfPersons",name);

                try {
                    System.out.println(Wworld.getCountry("USA").getFaculty("BOBY").getSumOfSalaries());
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("getSumOfSalaries",name);
            }
        };

        Thread threadLaborant = new Thread() {
            @Override
            public void run() {
                String name = getName();

                try {
                    Wworld.getCountry("USA").getFaculty("BOBY").printListOfOtherJobs();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfOtherJobs",name);

                try {
                    Wworld.getCountry("USA").getFaculty("BOBY").printFacultyEmployees();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printFacultyEmployees",name);
            }
        };

        Thread threadSecretary = new Thread() {
            @Override
            public void run() {
                String name = getName();

                try {
                    Wworld.printCountries();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printCountries",name);

                try {
                    Wworld.doesThisCountryExist("USA");
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("doesThisCountryExist",name);
                
                try {
                    Wworld.getCountry("USA").getFaculty("BOBY").printListOfSecretaries();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfSecretaries",name);

                try {
                    Wworld.getCountry("USA").getFaculty("BOBY").printListOfStudents();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfStudents",name);
            }
        };
        
        threadTeacher.start();
        threadStudent.start();
        threadLaborant.start();
        threadSecretary.start();

         //*/

        /*
        Thread thread = new Thread() {
            @Override
            public void run() {
                String name = getName();
                System.out.println(name);

                ///something
                try {
                    world.printCountries();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printCountries",name);

                try {
                    world.doesThisCountryExist("USA");
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("doesThisCountryExist",name);

                try {
                    world.doesThisCountryExist("Jamaica");
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("doesThisCountryExist",name);

                try {
                    world.printAllInfo();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printAllInfo",name);

                try {
                    world.printMedianSalaryInWorld();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printMedianSalaryInWorld",name);

                try {
                    world.printLowestSalaryinWorld();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printLowestSalaryinWorld",name);

                try {
                    world.printBigestSalaryinWorld();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printBigestSalaryinWorld",name);

                try {
                    System.out.println(world.getCountry("USA").getFaculty("BOBY").getNumberOfEmployees());
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("getNumberOfEmployees",name);

                try {
                    System.out.println(world.getCountry("USA").getFaculty("BOBY").getNumberOfPersons());
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("getNumberOfPersons",name);

                try {
                    System.out.println(world.getCountry("USA").getFaculty("BOBY").getSumOfSalaries());
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("getSumOfSalaries",name);

                try {
                    world.getCountry("USA").getFaculty("BOBY").printListOfTeachers();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfTeachers",name);

                try {
                    world.getCountry("USA").getFaculty("BOBY").printListOfLaborants();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfLaborants",name);

                try {
                    world.getCountry("USA").getFaculty("BOBY").printListOfSecretaries();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfSecretaries",name);

                try {
                    world.getCountry("USA").getFaculty("BOBY").printListOfStudents();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfStudents",name);

                try {
                    world.getCountry("USA").getFaculty("BOBY").printListOfOtherJobs();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printListOfOtherJobs",name);

                try {
                    world.getCountry("USA").getFaculty("BOBY").printFacultyEmployees();
                } catch (Exception e) {
                    System.out.println(e);
                }
                auditThread.printAuditAction("printFacultyEmployees",name);

            }
        };
        
        thread.start();
        //*/



        Application.launch(args);

        countryCSV.writeCountry(Wworld,"data/country.csv");
        facultyCSV.writeFaculties(Wworld,"data/faculties.csv");
        teacherCSV.writeTeachers(Wworld,"data/teachers.csv");
        laborantCSV.writeLaborants(Wworld,"data/laborants.csv");
        secretaryCSV.writeSecretaries(Wworld,"data/secretaries.csv");
        studentCSV.writeStudents(Wworld,"data/students.csv");
        otherJobsCSV.writeOtherJobs(Wworld,"data/otherJobs.csv");

        ///BD bd = BD.getInstance();

        /*
        Teacher teacher1 = new Teacher(24,2500,"ASC","no","none","Dragulici",50);
        Teacher teacher2 = new Teacher(14,2700,"POO","no","none","Alexe",40);
        Teacher teacher3 = new Teacher(34,2900,"IOI","yes","16:00-20:00","Johnny",66);

        Laborant laborant1 = new Laborant(13,1500,"ASC","no","Martin",33);
        Laborant laborant2 = new Laborant(23,1600,"POO","yes","Luthor",56);
        Laborant laborant3 = new Laborant(33,1700,"IOI","no","Licis",54);

        Secretary secretary1  = new Secretary(3,1800,"08:00-12:00","Laura",26);
        Secretary secretary2  = new Secretary(13,2300,"08:00-12:00","Lavinia",56);
        Secretary secretary3  = new Secretary(24,2800,"08:00-12:00","Ioana",66);

        Student student1 = new Student("Licence",2,"Jan",21);
        Student student2 = new Student("Master",2,"Boni",24);
        Student student3 = new Student("Licence",2,"Armani",25);

        OtherJobs otherJobs1 = new OtherJobs(3,2300,"instalator","Gica",43);
        OtherJobs otherJobs2 = new OtherJobs(23,2600,"portar","Gigel",54);
        OtherJobs otherJobs3 = new OtherJobs(13,3300,"decan","Gina",43);

        Faculty faculty1 = new Faculty("MIT");
        faculty1.addLaborant(laborant1);
        faculty1.addLaborant(laborant3);
        faculty1.addSecretary(secretary2);
        faculty1.addTeacher(teacher3);
        faculty1.addTeacher(teacher2);
        faculty1.addStudent(student3);
        faculty1.addStudent(student1);
        faculty1.addOtherJobs(otherJobs1);

        Faculty faculty2 = new Faculty("OXFORD");
        faculty2.addLaborant(laborant2);
        faculty2.addLaborant(laborant3);
        faculty2.addSecretary(secretary1);
        faculty2.addSecretary(secretary3);
        faculty2.addTeacher(teacher1);
        faculty2.addTeacher(teacher2);
        faculty2.addStudent(student2);
        faculty2.addStudent(student1);
        faculty2.addOtherJobs(otherJobs2);
        faculty2.addOtherJobs(otherJobs3);

        Faculty faculty3 = new Faculty("BOBY");
        faculty3.addTeacher(teacher3);

        Country country1 = new Country("USA");
        country1.addFaculty(faculty3);

        Country country2 = new Country("France");
        country2.addFaculty(faculty1);
        country2.addFaculty(faculty2);

        World world = new World();
        world.addCountry(country1);
        world.addCountry(country2);

        TeacherBD teacherBD = TeacherBD.getInstance();
        //teacherBD.createTeacher();

        LaborantBD laborantBD = LaborantBD.getInstance();
        //laborantBD.createLaborant();

        SecretaryBD secretaryBD = SecretaryBD.getInstance();
        //secretaryBD.createSecretary();

        OtherJobsBD otherJobsBD = OtherJobsBD.getInstance();
        //otherJobsBD.createOtherJobs();

        StudentBD studentBD = StudentBD.getInstance();
        //studentBD.createStudent();

         */


        /*
        List<String> a = new ArrayList<String>();
        List<String> b = new ArrayList<String>();
        List<String> c = new ArrayList<String>();
        for(Country country : world.getListOfCountries()) {
            for(Faculty faculty : country.getFaculties()) {
                for(Student student : faculty.getListOfStudents()) {
                    a.add(country.getCountryName());
                    b.add(faculty.getFacultyName());
                    c.add(student.getPersonName());
                }
            }
        }

        //*/

        /*
        ///aici adaug profesorii
        for(int i = 0; i < a.size() ; ++i) {
            studentBD.addStudent(world, world.getCountry(a.get(i)).getFaculty(b.get(i)).getStudent(c.get(i)), b.get(i), a.get(i));
        }
        //*/

        ///teacherBD.addTeacher(world,teacher2,"OXFORD","France");

        ///teacherBD.updateTeacher(world,teacher3,"OXFORD","France","Dragulici",4);
        /*
        teacherBD.deleteTeacher(world,1,"Johnny","BOBY","USA");
        teacherBD.deleteTeacher(world,2,"Johnny","MIT","France");
        teacherBD.deleteTeacher(world,3,"Alexe","MIT","France");
        teacherBD.deleteTeacher(world,4,"Dragulici","OXFORD","France");
        teacherBD.deleteTeacher(world,5,"Alexe","OXFORD","France");
        //*/
        //world.printAllInfo();
    }

    public static void etapa2() throws IOException {
        World world = new World();

        CountryReadWriteCSV countryCSV = CountryReadWriteCSV.getInstance();
        FacultiesReadWriteCSV facultyCSV = FacultiesReadWriteCSV.getInstance();
        TeacherReadWriteCSV teacherCSV = TeacherReadWriteCSV.getInstance();
        LaborantReadWriteCSV laborantCSV = LaborantReadWriteCSV.getInstance();
        SecretaryReadWriteCSV secretaryCSV = SecretaryReadWriteCSV.getInstance();
        StudentReadWriteCSV studentCSV = StudentReadWriteCSV.getInstance();
        OtherJobsReadWriteCSV otherJobsCSV = OtherJobsReadWriteCSV.getInstance();

        countryCSV.readCountry(world,"data/country.csv");
        facultyCSV.readFaculties(world,"data/faculties.csv");
        teacherCSV.readTeachers(world,"data/teachers.csv");
        laborantCSV.readLaborants(world,"data/laborants.csv");
        secretaryCSV.readSecretaries(world,"data/secretaries.csv");
        studentCSV.readStudents(world,"data/students.csv");
        otherJobsCSV.readOtherJobs(world,"data/otherJobs.csv");


        ///something
        world.printCountries();

        world.doesThisCountryExist("USA");
        world.doesThisCountryExist("Jamaica");

        world.printAllInfo();

        world.printMedianSalaryInWorld();

        world.printLowestSalaryinWorld();

        world.printBigestSalaryinWorld();

        System.out.println(world.getCountry("USA").getFaculty("BOBY").getNumberOfEmployees());
        System.out.println(world.getCountry("USA").getFaculty("BOBY").getNumberOfPersons());
        System.out.println(world.getCountry("USA").getFaculty("BOBY").getSumOfSalaries());

        world.getCountry("USA").getFaculty("BOBY").printListOfTeachers();
        world.getCountry("USA").getFaculty("BOBY").printListOfLaborants();
        world.getCountry("USA").getFaculty("BOBY").printListOfSecretaries();
        world.getCountry("USA").getFaculty("BOBY").printListOfStudents();
        world.getCountry("USA").getFaculty("BOBY").printListOfOtherJobs();
        world.getCountry("USA").getFaculty("BOBY").printFacultyEmployees();

        countryCSV.writeCountry(world,"data/country.csv");
        facultyCSV.writeFaculties(world,"data/faculties.csv");
        teacherCSV.writeTeachers(world,"data/teachers.csv");
        laborantCSV.writeLaborants(world,"data/laborants.csv");
        secretaryCSV.writeSecretaries(world,"data/secretaries.csv");
        studentCSV.writeStudents(world,"data/students.csv");
        otherJobsCSV.writeOtherJobs(world,"data/otherJobs.csv");
    }

    public static void etapa1() throws IOException {
        Teacher teacher1 = new Teacher(24,2500,"ASC","no","none","Dragulici",50);
        Teacher teacher2 = new Teacher(14,2700,"POO","no","none","Alexe",40);
        Teacher teacher3 = new Teacher(34,2900,"IOI","yes","16:00-20:00","Johnny",66);

        Laborant laborant1 = new Laborant(13,1500,"ASC","no","Martin",33);
        Laborant laborant2 = new Laborant(23,1600,"POO","yes","Luthor",56);
        Laborant laborant3 = new Laborant(33,1700,"IOI","no","Licis",54);

        Secretary secretary1  = new Secretary(3,1800,"08:00-12:00","Laura",26);
        Secretary secretary2  = new Secretary(13,2300,"08:00-12:00","Lavinia",56);
        Secretary secretary3  = new Secretary(24,2800,"08:00-12:00","Ioana",66);

        Student student1 = new Student("Licence",2,"Jan",21);
        Student student2 = new Student("Master",2,"Boni",24);
        Student student3 = new Student("Licence",2,"Armani",25);

        OtherJobs otherJobs1 = new OtherJobs(3,2300,"instalator","Gica",43);
        OtherJobs otherJobs2 = new OtherJobs(23,2600,"portar","Gigel",54);
        OtherJobs otherJobs3 = new OtherJobs(13,3300,"decan","Gina",43);

        Faculty faculty1 = new Faculty("MIT");
        faculty1.addLaborant(laborant1);
        faculty1.addLaborant(laborant3);
        faculty1.addSecretary(secretary2);
        faculty1.addTeacher(teacher3);
        faculty1.addTeacher(teacher2);
        faculty1.addStudent(student3);
        faculty1.addStudent(student1);
        faculty1.addOtherJobs(otherJobs1);

        Faculty faculty2 = new Faculty("OXFORD");
        faculty2.addLaborant(laborant2);
        faculty2.addLaborant(laborant3);
        faculty2.addSecretary(secretary1);
        faculty2.addSecretary(secretary3);
        faculty2.addTeacher(teacher1);
        faculty2.addTeacher(teacher2);
        faculty2.addStudent(student2);
        faculty2.addStudent(student1);
        faculty2.addOtherJobs(otherJobs2);
        faculty2.addOtherJobs(otherJobs3);

        Faculty faculty3 = new Faculty("BOBY");
        faculty3.addTeacher(teacher3);

        Country country1 = new Country("USA");
        country1.addFaculty(faculty3);

        Country country2 = new Country("France");
        country2.addFaculty(faculty1);
        country2.addFaculty(faculty2);

        World world = new World();
        world.addCountry(country1);
        world.addCountry(country2);


        world.printCountries();

        world.doesThisCountryExist("USA");
        world.doesThisCountryExist("Jamaica");

        world.printAllInfo();

        world.printMedianSalaryInWorld();

        world.printLowestSalaryinWorld();

        world.printBigestSalaryinWorld();
    }
}
