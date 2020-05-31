
package modelo;

/**
 *
 * @author PORTO
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import modelo.Contacto;

public class ConsultaAgenda 
{
   public static LinkedList<Contacto> getContactos()
   {
      LinkedList<Contacto> listaContactos=new LinkedList<Contacto>();
      try
      {
         Class.forName("org.gjt.mm.mysql.Driver");
         Connection conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crud", "root", "root");
         Statement st = conexion.createStatement();
         ResultSet rs = st.executeQuery("select * from musuario" );
         while (rs.next())
         {
            Contacto contacto = new Contacto();
            contacto.setId(rs.getInt("id_usr"));
            contacto.setUser(rs.getString("username"));
            contacto.setPassword(rs.getString("password"));
            contacto.setNivel(rs.getInt("nivel"));
            listaContactos.add(contacto);
         }
      }
      catch (Exception e)
      {
          System.out.println("No se conecto a consultar ");
         e.printStackTrace();
      }
      return listaContactos;
   }
} 
