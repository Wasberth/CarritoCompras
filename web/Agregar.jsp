<%-- 
    Document   : Agregar
    Created on : 22/04/2020, 08:29:58 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Page</title>
    </head>
    <style>
        h2{
            text-align: center;
            font-family: arial;
            color: red;
        };
        td{
            text-align: center;
            font-family: Verdana;
            font-size: 16; 
        };
    </style>
    
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
    
    %>
    
    <body>
        
        <h1>Agrega un nuevo item</h1>
        <table border = "1" width="50">
            <tr>
                <td width="100%">
                    <form mehod="post" action="GuardarItem.jsp">
                        <h2>
                            Datos a Insertar
                        </h2>
                        <table border="1" width="100%">
                            <tr>
                                <td width="50%">
                                    <b>
                                        Nombre
                                    </b>
                                </td>
                                <td width="50%">
                                    <input type="text" name="nombre">
                                </td> 
                            </tr>
                            
                            <tr>
                                <td width="50%">
                                    <b>
                                        Descripcion
                                    </b>
                                </td>
                                <td >
                                    <input type="text" name="descr">
                                </td> 
                            </tr>
                            
                            <tr>
                                <td width="50%">
                                    <b>
                                        Precio
                                    </b>
                                </td>
                                <td width="50%">
                                    <input type="text" name="precio">
                                </td> 
                            </tr>
                            
                            <tr>
                                <td width="50%">
                                    <b>
                                        Stock
                                    </b>
                                </td>
                                <td width="50%">
                                    <input type="text" name="stock">
                                </td> 
                            </tr>
                        </table>
                        
                        <input type="submit" value="Agregar item">
                        <input type="reset" value="Reinicar">
                       
                    </form>
                </td>
            </tr>
            
        </table>
        
        <a href="Lista.jsp">
            Regresar al Menu de Administradores
        </a>
        
    </body>
</html>
