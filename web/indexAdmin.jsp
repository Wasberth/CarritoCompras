<%-- 
    Document   : indexAdmin
    Created on : 21/04/2020, 09:56:09 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administradores</title>
    </head>
    <body>
        <%
        
        HttpSession sesion = request.getSession();
        String usuario;
        
        if(sesion.getAttribute("user")!=null){
            usuario = sesion.getAttribute("user").toString();
            out.append("<a href='index.jsp'?cerrar=true><h5>Cerrar Sesion "+usuario+"</h5></a>");    
        }else{
            out.append("<script>location.replace['login.jsp'];</script>");
        }
        
        %>
        <h1>Estás en la página de administradores (Todavia no tiene diseño)</h1>
        <br>
        <a href="Agregar.jsp">Agregar articulo</a><br>
        <a href="ConsultarAdmin.jsp">Consultar perfiles</a><br>
        <a href="ModificarArticuloAdmin.jsp">Modificar articulo</a><br>
        <a href="DeleteArticuloAdmin.jsp">Eliminar articulo</a><br>
    </body>
</html>
