/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelado.Compra;
/**
 *
 * @author PORTO
 */
public class CompraDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement pst;
    ResultSet rs;
    PreparedStatement pst2;
    ResultSet rs2;
    int r=0;
    
    public int generarCompra(Compra compra){
        int idCompra;
        String sql = "INSERT INTO dcompra(id_cc,id_prod,cantidad_cc,subtotal,total_cc,fecha)values(?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, compra.getCliente().getId());
            pst.setInt(2, compra.getIdpago());
            pst.setInt(3,compra.getCantidad());
            pst.setDouble(4,compra.getMonto());
            pst.setDouble(5,compra.getMonto());
            pst.setString(6, compra.getFecha());
            pst.executeUpdate();
            
            
            sql = "SELECT @@IDENTY AS id_cc";
            rs = pst.executeQuery(sql);
            rs.next();
            idCompra = rs.getInt("id_cc");
            rs.close();
            
            for(carrito detalle : compra.getDetalleCompras()){
                sql = "INSERT INTO eclientecom(id_cli,id_cc)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, compra.getCliente().getId());
                pst.setInt(2, compra.getIdpago());
                r = pst.executeUpdate();
            }
        } catch (Exception e) {
        }
        
        return r;
    }
    
}
