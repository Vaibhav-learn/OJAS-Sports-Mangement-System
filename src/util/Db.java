package util;
import java.sql.*;
public class Db {
        private static final String url = "jdbc:mysql://localhost:3306/OJAS"; 
        private static final String username7 = "root"; 
        private static final String password7 = "scieneer"; 
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // loading MySQL driver
            return DriverManager.getConnection(url,username7,password7);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return null;
}
}