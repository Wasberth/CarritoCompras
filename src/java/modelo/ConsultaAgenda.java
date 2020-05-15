
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

public class ConsultaAgenda 
{
   public static LinkedList<Contacto> getContactos()
   {
      LinkedList<Contacto> listaContactos=new LinkedList<Contacto>();
      try
      {
         Class.forName("org.gjt.mm.mysql.Driver");
         Connection conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost/Crud", "root", "root");
         Statement st = conexion.createStatement();
         ResultSet rs = st.executeQuery("select * from users" );
         while (rs.next())
         {
            Contacto contacto = new Contacto();
            contacto.setId(rs.getInt("id"));
            contacto.setUser(rs.getString("user"));
            contacto.setPassword(rs.getString("password"));
            contacto.setNivel(rs.getInt("nivel"));
            listaContactos.add(contacto);
         }
         rs.close();
         st.close();
         conexion.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return listaContactos;
   }
} 