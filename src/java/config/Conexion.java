package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/Crud";
    String user="root";
    String pass="root";
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se conecto");
            e.getMessage();
        }
        return con;
    }
    
}
