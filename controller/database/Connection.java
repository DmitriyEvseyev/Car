package controller.database;

import java.sql.DriverManager;

public class Connection {
    public static java.sql.Connection connection;
    public static void connection()  {
        try {
            Class.forName("org.postgresql.Driver");
            connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/CAR",
                    "postgres", "5b6eu5");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
