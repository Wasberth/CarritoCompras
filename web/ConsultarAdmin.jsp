<%-- 
    Document   : ConsultarAdmin
    Created on : 22/04/2020, 08:43:41 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page language="java" %>
<%@ page import = "modelo.Contacto"%> 
<%@ page import = "modelo.ConsultaAgenda"%> 
<%@ page import = "java.util.LinkedList"%> 
<html>
    <body>
        <h1>Consulta a base de datos</h1>
        <table border="1">
            <tr>
                <td>id</td>
                <td>user</td>
                <td>password</td>
                <td>nivel</td>
            </tr>
            <%
                LinkedList<Contacto> lista = ConsultaAgenda.getContactos();
                for (int i = 0; i < lista.size(); i++) {
                    out.println("<tr>");
                    out.append("<td>" + lista.get(i).getId() + "</td>");
                    out.println("<td>" + lista.get(i).getUser()+ "</td>");
                    out.println("<td>" + lista.get(i).getPassword()+ "</td>");
                    out.println("<td>" + lista.get(i).getNivel()+ "</td>");
                    out.println("</tr>");
                }
            %>
        </table>
    </body>
</html>