<%-- 
    Document   : ModificarArticuloAdmin
    Created on : 31/05/2020, 12:44:25 AM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Modificar</title>
    </head>
    <body>
        <h1>Ingrese el nombre del articulo a modificar</h1>
        
        <form action="Modificar.jsp" method="post" name="form-modificar">
            nombre: <input type="text" name="nombre_producto" required="true"/><br/>
                     
        <h1>Ingrese los datos a Modificar</h1>
             
        Stock nuevo: <input type="number" name="stock_prod" min="1" max="999"/><br/><br/>
        Descripcion nueva: <input type="text" name="desc_prod"/><br/><br/>
        Precio nuevo: <input type="number" name="precio_prod" min="1" max="100000"/><br/><br/>
         <input type="submit" value="Modificar"/>
         
        </form>
        
        
        <br><br>
                <a href="indexAdmin.jsp">Volver a Menú</a>
        
    </body>
</html>