<%-- 
    Document   : GuardarItem
    Created on : 22/04/2020, 08:32:35 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AgregarItem Page</title>
    </head>
    <body>
        <%@page language="java" import="java.sql.*, java.util.*, java.text.*"%>
        
        <%
        out.append("<style> p (font-family: arial;"
                + "color: blue; font-size: 16);"
                + "</style>"
                + "<style> a,b (font-family: arial;"
                + "color: red; font-size: 16);"
                + "</style>"
                + "<style> a.space (font-family: arial;"
                + "color: green; font-size: 18);"
                + "margin: 0 0 0 208px;"
                + "</style>");
        //CREANDO LA CONEXION CON BD
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "root";
        String bd = "Crud";
        
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url+bd, user, password);
            try {
                Statement sst = con.createStatement();
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descr");
                double precio = Double.parseDouble(request.getParameter("precio"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                
                String sql = "insert into producto (Nombres,Descripcion,Precio,Stock) "
                        + "values ('"+nombre+"','"+descripcion+"','"+precio+"','"+stock+"')";
                int val = sst.executeUpdate(sql);
                con.close();
            } catch (SQLException e) {
                System.out.println("No se conecto a la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getSQLState());
                System.out.println(e.getStackTrace());
            }
                
        } catch (Exception e) {
                System.out.println("No se conecto a la base");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
        }
        %>
        
        <h1>Muchas gracias por tu registro</h1>
        <a href="indexAdmin.jsp">
            Regresar al Menu
        </a>
        <br>
        <a href="Agregar.jsp">
            Insertar otro registro
        </a>
    </body>
</html>
