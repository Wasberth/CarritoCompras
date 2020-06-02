<%-- 
    Document   : Guardar
    Created on : 21/04/2020, 10:58:25 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardar jsp</title>
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
        String nombre = "", contra = "";
        
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url+bd, user, password);
            try {
                Statement sst = con.createStatement();
                int id = 2;
                nombre = request.getParameter("user");
                contra = request.getParameter("password");
                System.out.println(nombre);
                String sql = "insert into MUsuario (username, password)"
                        + " values ('"+nombre+"','"+contra+"')";
                System.out.println(sql);
                int val = sst.executeUpdate(sql);
                con.close();
                out.append("Registro exitoso");
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
        response.sendRedirect("index.jsp?btninicar=1&user="+nombre+"&nivel=2&password="+contra);
        %>
    </body>
</html>
