<%-- 
    Document   : Delete
    Created on : 31/05/2020, 04:18:36 PM
    Author     : PORTO
--%>

<%@ page language="java" %>     
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelado.ModificaAgenda" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Agenda</title>
    </head>
    <body>        
         <%
             
            String nombre,descripcion;
            double precio;
            int stock; 
            
            nombre=request.getParameter("nombre_producto");
            ModificaAgenda objmodif=new ModificaAgenda();
            if (objmodif.eliminarArticulo(nombre)==true){
                out.println("Articulo eliminado correctamente");

            } else {
                out.println("Hubo un error en la eliminacion del artículo y la razón principal es: ");
                out.append("1. No existe el articulo\n");
            }
 %>
          
        
        <br>
                <a href="indexAdmin.jsp">Volver a Menú</a><br>
                <a href="DeleteArticuloAdmin.jsp">Modificar otro artículo</a>
    </body>
</html>
