package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
                p.setFoto(rs2.getBinaryStream("img_prod"));
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
            System.out.println("1");
            pst = con.prepareStatement(sql);
            System.out.println("2");
            pst2 = con.prepareStatement(sql2);
            System.out.println("3");
            rs = pst.executeQuery();
            System.out.println("4");
            rs2 = pst2.executeQuery();
            System.out.println("5");
            while (rs.next() && rs2.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs2.getString("nom_mprod"));
                p.setFoto(rs.getBinaryStream("img_prod"));
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

    public void listarImg(int id, HttpServletResponse response) {
        /*String sql = "SELECT * FROM DProducto WHERE idProducto="+id;
        InputStream inputStream = null;
        OutputStream outputStream;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                inputStream = rs.getBinaryStream("img_prod");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while((i = bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
        } catch (IOException | SQLException e) {
        }*/

    }

}
