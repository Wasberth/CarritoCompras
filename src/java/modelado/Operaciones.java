package modelado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Ticket;

public class Operaciones {

    private static int sell;
    private static final List<Integer> NOSTOCK = new ArrayList<Integer>();
    String driver;
    String url;
    String user;
    String passwordBd;

    public Operaciones() {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/Crud";
        user = "root";
        passwordBd = "root";
    }

    public static List<Integer> getNOSTOCK() {
        return NOSTOCK;
    }
    
    public static void clearNOSTOCK(){
        NOSTOCK.clear();
    }

    //Entero por que retorna el nivel de usuario
    public int loguear(String usuario, String password) {
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int cont = 0;
        int nivel = 0;
        String sql = "SELECT `nivel` FROM MUsuario WHERE username='" + usuario + "' and password='" + password + "'";
        try {

            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
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

    public int getLastSell() {
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int lastSell = 0;
        String sql = "SELECT `id_com` FROM MCompra ORDER BY `id_com` DESC LIMIT 1";
        try {

            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                lastSell = rs.getInt(1);
                System.out.println(lastSell);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error");
            e.getMessage();
            e.printStackTrace();
        }

        return lastSell;
    }

    public String comprar(Ticket ticket) {
        String resultado = "";
        boolean res = true;
        sell = new Operaciones().getLastSell() + 1;

        res = res && new Operaciones().guardarDetallesCompra(ticket.getSubtotal(), ticket.getTotal(), ticket.getFecha());
        res = res && new Operaciones().registrarCompra();
        for (int i = 0; i < ticket.getProductos().size(); i++) {
            if (new Operaciones().checkStock(ticket.getIds().get(i), ticket.getCantidades().get(i))) {
                res = res && new Operaciones().actualizarStock(ticket.getIds().get(i), ticket.getCantidades().get(i));
                res = res && new Operaciones().guardarTicket(ticket.getIds().get(i), ticket.getCantidades().get(i));
            } else {
                NOSTOCK.add(ticket.getIds().get(i));
                resultado = resultado + "No hay stock suficiente de " + ticket.getProductos().get(i) + ". ";
            }
        }
        sell = -1;
        if (!res) {
            resultado = "Un error ha ocurrido. " + resultado;
        }
        if ("".equals(resultado)) {
            resultado = "Success";
        }
        System.out.println(resultado);
        return resultado;
    }

    public boolean checkStock(int idprod, int cantidad) {
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int stock = 0;
        String sql = "SELECT `stock_prod` FROM DProducto WHERE id_dprod = " + idprod;
        try {

            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                stock = rs.getInt(1);
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error");
            e.getMessage();
            e.printStackTrace();
            return false;
        }

        return stock >= cantidad;
    }

    private boolean registrarCompra() {
        Connection conn;
        PreparedStatement pst;
        int rs;
        String sql = "INSERT INTO MCompra (id_com, d_com) VALUES (" + sell + ", " + sell + ")";
        try {
            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(sql);
            System.err.println(e);
            return false;
        }
        return true;
    }

    private boolean guardarDetallesCompra(double subtotal, double total, String fecha) {
        Connection conn;
        PreparedStatement pst;
        int rs;
        String sql = "INSERT INTO DCompra (id_cc, subtotal, total_cc, fecha) VALUES (" + sell + ", " + subtotal + "," + total + ", '" + fecha + "')";
        try {

            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(sql);
            System.err.println(e);
            return false;
        }
        return true;
    }

    private boolean actualizarStock(int id, int cantidad) {
        Connection conn;
        PreparedStatement pst;
        int rs;
        String sql = "UPDATE DProducto SET stock_prod = stock_prod - " + cantidad + " WHERE id_dprod =" + id;
        try {

            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(sql);
            System.err.println(e);
            return false;
        }
        return true;
    }

    private boolean guardarTicket(int idprod, int cantidad) {
        Connection conn;
        PreparedStatement pst;
        int rs;
        String sql = "INSERT INTO ETicket (id_com, id_mprod, cantidad) VALUES (" + sell + ", " + idprod + "," + cantidad + ")";
        try {

            Class.forName(this.driver);
            conn = (Connection) DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.passwordBd
            );
            pst = (PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(sql);
            System.err.println(e);
            return false;
        }
        return true;
    }

}
