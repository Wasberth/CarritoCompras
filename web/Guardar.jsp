<%-- 
    Document   : Guardar
    Created on : 21/04/2020, 10:58:25 PM
    Author     : PORTO
--%>

<%@page import="config.AtributosRegistro"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guardar jsp</title>
    </head>
    <body>
        
        
        
        <%
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
                nombre = request.getParameter("user");
                contra = request.getParameter("password");
                System.out.println("GUARDAR JSP: ,\n"+nombre+"\n"+contra);
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
        AtributosRegistro ar = new AtributosRegistro(nombre,contra);
        String usuario_r = AtributosRegistro.usuario;
                        String password_r = AtributosRegistro.password;
                        System.out.println(usuario_r);
                        System.out.println(password_r);
        response.sendRedirect("index.jsp?btniniciar=1&user="+usuario_r+"&nivel=2&password="+password_r);
        %>
    </body>
</html>
