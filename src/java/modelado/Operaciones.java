package modelado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Ticket;

public class Operaciones {

    private static int sell;
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

    //Entero por que retorna el nivel de usuario
    public int loguear(String usuario, String password) {
        System.out.println(usuario);
        System.out.println(password);
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

    public void comprar(Ticket ticket) {
        sell = new Operaciones().getLastSell() + 1;
        new Operaciones().guardarDetallesCompra(ticket.getSubtotal(), ticket.getTotal(), ticket.getFecha());
        new Operaciones().registrarCompra();
        for (int i = 0; i < ticket.getProductos().size(); i++) {
            System.out.println("id");
            System.out.println(ticket.getIds().get(i));
            System.out.println("cantidad");
            System.out.println(ticket.getCantidades().get(i));
            new Operaciones().guardarTicket(ticket.getIds().get(i), ticket.getCantidades().get(i));
        }
        sell = -1;
    }

    private void registrarCompra() {
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
        }
    }

    private void guardarDetallesCompra(double subtotal, double total, String fecha) {
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
        }
    }

    private void guardarTicket(int idprod, int cantidad) {
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
        }
    }

}
