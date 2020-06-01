package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;

public class ProductoDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    PreparedStatement pst2;
    ResultSet rs2;

    public Producto listarId(int id) {
        String sql = "SELECT * FROM MProducto where id_mprod=" + id;

        String sql2 = "SELECT * FROM dproducto where id_dprod=" + id;

        Producto p = new Producto();

        try {
            con = cn.getConnection();
            pst = con.prepareStatement(sql);
            pst2 = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            rs2 = pst2.executeQuery();
            while (rs.next() && rs2.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString("nom_mprod"));
                p.setFoto(rs2.getString("img_prod"));
                p.setDescripcion(rs2.getString("desc_prod"));
                p.setPrecio(rs2.getDouble("precio_prod"));
                p.setStock(rs2.getInt("stock_prod"));
            }
        } catch (SQLException e) {
            System.out.println("Error en listar Id");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return p;
    }

    public List listar() {
        List<Producto> productos = new ArrayList();
        String sql = "SELECT * FROM DProducto";
        String sql2 = "SELECT * FROM MProducto";
        try {
            con = cn.getConnection();
            pst = con.prepareStatement(sql);
            pst2 = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            rs2 = pst2.executeQuery();
            while (rs.next() && rs2.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs2.getString("nom_mprod"));
                p.setFoto(rs.getString("img_prod"));
                p.setDescripcion(rs.getString("desc_prod"));
                p.setPrecio(rs.getDouble("precio_prod"));
                p.setStock(rs.getInt("stock_prod"));
                productos.add(p);

            }

        } catch (SQLException e) {
            System.out.println("ERROR EN SQL :C");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return productos;
    }

    public boolean verificar(String nom_mprod) {
        System.out.println("Nombre recibido en productodao " + nom_mprod);
        List<Producto> productos = new ArrayList();
        String sql2 = "SELECT * FROM mproducto";
        try {
            boolean verificador = false;
            con = cn.getConnection();
            pst2 = con.prepareStatement(sql2);
            rs2 = pst2.executeQuery();
            
            while (rs2.next()) {
                Producto p = new Producto();
                p.setNombres(rs2.getString("nom_mprod"));
                String nombreTabla = rs2.getString("nom_mprod");
                System.out.println("Articulo de la tabla: "+nombreTabla);
                productos.add(p);
                
            }
            for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).getNombres().equalsIgnoreCase(nom_mprod)) {
                        System.out.println("Existe 1");
                        verificador = true;
                        break;
                    } else {
                        System.out.println("No existe 1");
                        verificador = false;
                    }
                }//Termina for
                if (verificador == true) {
                    return true;
                } else {
                    return false;
                }
//            con.close();
//            pst2.close();
//            rs2.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN SQL :C");
            System.out.println(e.getMessage());
            return false;

        }

    }

    

}
