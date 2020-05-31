<%-- 
    Document   : Modificar
    Created on : 31/05/2020, 01:04:01 AM
    Author     : PORTO
--%>

<%@ page language="java" %>
<%@ page import = "Bean.Contacto"%>
<%@ page import = "Bean.ModificaAgenda"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Agenda</title>
    </head>
    <body>
        <h1>Los datos se han actualizado correctamente</h1>
        
         <%
             
            String Nombre,descripcion;
            double precio;
            
                        Nombre=request.getParameter("nom_prod");
            descripcion=request.getParameter("desc_prod");
            precio=Double.parseDouble(request.getParameter("precio_prod"));
            ModificaAgenda objmodif=new ModificaAgenda();
            if (objmodif.modificarUsuario(Nombre,descripcion,precio)){
                out.println("articulo actualizado correctamente");

            } else {
                out.println("Error en la actualización");
                
            }
 %>
          
        
        <br><br>
                <a href="indexAdmin.jsp">Volver a Menú</a>
    </body>
</html>
