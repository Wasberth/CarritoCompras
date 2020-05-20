package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PORTO
 */
public class ProductoDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    PreparedStatement pst2;
    ResultSet rs2;
    
    public Producto listarId(int id){
        String sql = "SELECT * FROM MProducto where id_mprod="+id;
        Producto p = new Producto();
        try {
            con =cn.getConnection();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return p;
    }
    
    public List listar(){
        List<Producto>productos=new ArrayList();
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
            while(rs.next() && rs2.next()){
                Producto p = new Producto();
                p.setId(rs.getInt("id_dprod"));
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
    
    public void listarImg(int id, HttpServletResponse response){
        String sql = "SELECT * FROM DProducto WHERE idProducto="+id;
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
        }
        
    }
    
    
}
