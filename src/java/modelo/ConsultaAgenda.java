
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
import modelo.Producto;

public class ConsultaAgenda 
{
   public static LinkedList<Contacto> getContactos()
   {
      LinkedList<Contacto> listaContactos=new LinkedList<Contacto>();
      try
      {
         Class.forName("org.gjt.mm.mysql.Driver");
         Connection conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crud", "root", "n0m3l0");
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
   
   public static LinkedList<Producto> getProductos(){
       LinkedList<Producto> listaContactos=new LinkedList<Producto>();
      try
      {
         Class.forName("org.gjt.mm.mysql.Driver");
         Connection conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crud", "root", "n0m3l0");
         Statement st = conexion.createStatement();
         Statement st2 = conexion.createStatement();
         ResultSet rs = st.executeQuery("select * from mproducto" );
         ResultSet rs2 = st2.executeQuery("select * from dproducto" );
         while (rs.next() && rs2.next())
         {
            Producto contacto = new Producto();
            contacto.setId(rs.getInt("id_mprod"));
            contacto.setNombres(rs.getString("nom_mprod"));
            contacto.setDescripcion(rs2.getString("desc_prod"));
            contacto.setPrecio(rs2.getDouble("precio_prod"));
            contacto.setStock(rs2.getInt("stock_prod"));
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
