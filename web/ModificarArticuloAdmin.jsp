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
        <h1>Ingrese el id del articulo a modificar</h1>
        
        <form action="" method="post" name="form-modificar">
        nombre: <input type="text" name="nombre_producto"/><br/>
                     
        <h1>Ingrese los datos a Modificar</h1>
             
        Nombre nuevo: <input type="text" name="nom_prod"/><br/><br/>
        Descripcion nueva: <input type="text" name="desc_prod"/><br/><br/>
        Precio nuevo: <input type="number" name="precio_prod"/><br/><br/>
         <input type="submit" value="Modificar"/>
         
        </form>
        
        
        <br><br>
                <a href="Principal.jsp">Volver a Menú</a>
        
    </body>
</html>