package Services;

import java.sql.*;

public class BD {
    public static BD instance;
    private static final String URL = "jdbc:mysql://localhost:3306/pao";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static Connection connection;


    private BD() throws SQLException {
        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(URL,USER,PASS);
        System.out.println("Connected  to database");
    }

    public static BD getInstance() throws SQLException {
        if (instance == null) {
            instance = new BD();
        }

        if (instance.getConnection().isClosed()) {
            instance = new BD();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}