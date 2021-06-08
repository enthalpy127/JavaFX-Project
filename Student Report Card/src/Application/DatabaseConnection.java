package Application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){

        //String databaseName = "student_report_card";
        //String userName = "root";
        //String password = "************"; //      :)
        //String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";

        String url1 = "jdbc:sqlite:"+ System.getProperty("user.dir") +"\\..\\..\\..\\database\\student_report_card.db";

        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(System.getProperty("user.dir"));
            Class.forName("org.sqlite.JDBC");
            System.out.println("Connecting to SQLite database");
            databaseLink = DriverManager.getConnection(url1);
            System.out.println("Connection to SQLite database has been established.");
        }catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
