<%-- 
    Document   : Modificar
    Created on : 31/05/2020, 01:04:01 AM
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
            descripcion=request.getParameter("desc_prod");
            precio=Double.parseDouble(request.getParameter("precio_prod"));
            stock = Integer.parseInt(request.getParameter("stock_prod"));
            System.out.println("Articulos recibidos en modificar.jsp \n"+nombre+"\n"+descripcion+"\n"+precio+"\n"+stock);
            ModificaAgenda objmodif=new ModificaAgenda();
            if (objmodif.modificarArticulo(nombre,descripcion,precio,stock)==true){
                out.println("Articulo actualizado correctamente");

            } else {
                out.println("Hubo un error en la actualización del artículo y puede tener varias razones: ");
                out.append("1. No existe el articulo\n");
                out.append("2. Precios invalidos.\n");
                out.append("3. Stock invalidos.\n");
            }
 %>
          
        
        <br>
                <a href="indexAdmin.jsp">Volver a Menú</a><br>
                <a href="ModificarArticuloAdmin.jsp">Modificar otro artículo</a>
    </body>
</html>
