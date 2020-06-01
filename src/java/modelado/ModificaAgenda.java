package modelado;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import modelo.ProductoDAO;

public class ModificaAgenda {

    public boolean modificarArticulo(String nom_prod2, String desc_prod, double precio_prod, int stock2) {
        Connection con = null;
        ResultSet rs;
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "n0m3l0";
        String bd = "Crud";
        String nom_mprod = "", descripcion = "";
        double precio;
        int stock;
        String driver = "com.mysql.jdbc.Driver";

        if (precio_prod < 1 || stock2 < 1) {
            return false;
        } else {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url + bd, user, password);
                Statement stmt = con.createStatement();
                Statement stmt1 = con.createStatement();

                nom_mprod = nom_prod2;
                precio = precio_prod;
                descripcion = desc_prod;
                stock = stock2;
                int id_mprod = 0;

                ProductoDAO objp = new ProductoDAO();
                if (objp.verificar(nom_mprod)) {
                    System.out.println("Existe");
                    String sql = "SELECT id_mprod FROM mproducto WHERE nom_mprod='" + nom_mprod + "'";
                    System.out.println("sql: \n"+sql);
                    rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        id_mprod = rs.getInt("id_mprod");
                    }

                    String sql1 = "UPDATE dproducto SET precio_prod=" + precio + ", desc_prod='" + descripcion + "', stock_prod=" + stock + " WHERE id_dprod=" + id_mprod + "";
                    int rs1 = stmt.executeUpdate(sql1);

                    return true;
                } else {
                    System.out.println("No existe");
                    return false;
                }

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error SQL en MODIFICAAGENDA.JAVA");
                e.printStackTrace();
                return false;
            }
        }
    }
    
    public boolean eliminarArticulo(String nom_prod2){
        System.out.println("Eliminar articulo");
        Connection con = null;
        ResultSet rs;
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "n0m3l0";
        String bd = "Crud";
        String nom_mprod = "", descripcion = "";
        double precio;
        int stock;
        String driver = "com.mysql.jdbc.Driver";

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url + bd, user, password);
                Statement stmt = con.createStatement();
                Statement stmt2 = con.createStatement();
                Statement stmt3 = con.createStatement();

                nom_mprod = nom_prod2;
                int id_mprod = 0;

                ProductoDAO objp = new ProductoDAO();
                if (objp.verificar(nom_mprod)) {
                    System.out.println("Existe");
                    String sql = "SELECT id_mprod FROM mproducto WHERE nom_mprod='" + nom_mprod + "'";
                    System.out.println("sql: \n"+sql);
                    rs = stmt.executeQuery(sql);
                    while(rs.next()){
                        id_mprod = rs.getInt("id_mprod");
                    }
                    System.out.println("id: "+id_mprod);

                    String sql2 = "DELETE FROM mproducto WHERE id_mprod = "+id_mprod+"";
                    String sql3 = "DELETE FROM dproducto WHERE id_dprod = "+id_mprod+"";
                    int rs2 = stmt2.executeUpdate(sql2);
                    int rs3 = stmt3.executeUpdate(sql3);
                    return true;
                } else {
                    System.out.println("No existe");
                    return false;
                }
                
                
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error SQL en MODIFICAAGENDA.JAVA");
                e.printStackTrace();
                return false;
            }
            
        }      
}
