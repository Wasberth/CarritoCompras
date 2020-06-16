<%-- 
    Document   : Logout
    Created on : 30/05/2020, 09:12:23 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrando sesion...</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();

            if (sesion.getAttribute("user") != null && sesion.getAttribute("nivel") != null) {
                //session.invalidate();
                sesion.removeAttribute("user");
                sesion.removeAttribute("nivel");
                response.sendRedirect("index.jsp");
            } else {
                out.append("<script>location.replace['index.jsp'];</script>");
            }
            out.append("Espera estamos cerrando tu sesión");

        %>    
    </body>
</html>