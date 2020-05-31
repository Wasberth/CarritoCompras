<%-- 
    Document   : DeleteArticuloAdmin
    Created on : 31/05/2020, 04:17:31 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Page</title>
    </head>
    <body>
        <h1>Ingrese el nombre del articulo a eliminar</h1>

        <form action="Delete.jsp" method="post" name="form-modificar">
            Nombre del artículo: <input type="text" name="nombre_producto" required="true"/><br/>
            <input type="submit" value="Eliminar"/>

        </form>


        <br><br>
        <a href="indexAdmin.jsp">Volver a Menú</a>
    </body>
</html>
