package modelado;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Operaciones {
    
    String driver;
    String url;
    String user;
    String passwordBd;
    
    public Operaciones(){
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/Crud";
        user="root";
        passwordBd="n0m3l0";
    }
    
    //Entero por que retorna el nivel de usuario
    public int loguear(String usuario,String password){
        System.out.println(usuario);
        System.out.println(password);
            Connection conn;
            PreparedStatement pst;
            ResultSet rs;
            int cont=0;
            int nivel = 0;
            String sql = "SELECT `nivel` FROM MUsuario WHERE username='"+usuario+"' and password='"+password+"'";
        try {
            
            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
            this.url,
            this.user,
            this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                nivel = rs.getInt(1);
                System.out.println(nivel);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error");
            e.getMessage();
            e.printStackTrace();
        }
        
        return nivel;
    }
    
    
    
}
