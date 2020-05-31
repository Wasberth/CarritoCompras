/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.Conexion;
import java.sql.DriverManager;


public class ModificaAgenda extends Conexion {
    PreparedStatement stmt = null;
    PreparedStatement stmt1 = null;
    
    public boolean modificarUsuario(String nom_prod, String desc_prod, int precio_prod){
                   
    try
      {
          Class.forName("org.gjt.mm.mysql.Driver");
         Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crud", "root", "root");  
          String sql = "UPDATE mproducto SET nom_mprod=? WHERE id_dprod=?";
          String sql1 = "UPDATE dproducto SET precio_prod=?, desc_prod=? WHERE id_dprod=?";
          
          stmt = con.prepareStatement(sql);
          stmt1 = con.prepareStatement(sql1);
          
          stmt.setString(2, nom_prod);
          
          stmt.executeUpdate();
          
          
          stmt1.setInt(2, precio_prod);
          stmt1.setString(5, desc_prod);
          
          
          stmt1.executeUpdate();
          
          if(stmt != null && stmt1 != null) {
              //con.close();
              stmt.close();
              stmt = null;
              
              stmt1.close();
              stmt1 = null;
              
              return true;
          }
          
        } catch (Exception e)  {  
          return false;
           }
        return true;
        }
    
}
