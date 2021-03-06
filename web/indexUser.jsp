<%-- 
    Document   : indexUser
    Created on : 22/04/2020, 03:28:22 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/ali.css">
        <link href="css/estilos.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenida</title>
    </head>

    <body>
        <%
            HttpSession sesion = request.getSession();
            String nombre = "";
            int nivel = 0;
            try {

                sesion.getAttribute("user");
                nivel = Integer.parseInt(sesion.getAttribute("nivel").toString());

                nombre = sesion.getAttribute("user").toString();
                System.out.println("algo");
            } catch (Exception e) {
                response.sendRedirect("ErrorPage.jsp");
            }

        %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">COVID-19 Shop</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="Controlador?accion=home">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Controlador?accion=Carrito"><i class="fas fa-cart-plus">(<label style="color: orange">${contador}</label>)</i>Carrito de compras de <%out.append(nombre);%></a>
                    </li>
                    <li class="nav-item">
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Logout.jsp"><i class="fas fa-cart-plus" >Cerrar Sesión </i></a>
                    </li>
                    <%
                        if (nivel == 1) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="indexAdmin.jsp"><i class="fas fa-cart-plus" >Página de administradores.</i></a>
                    </li>
                    <%
                    }
                    %>
                </ul>

            </div>
        </nav>

        <%
            Object PSS = request.getAttribute("mensaje");
            System.out.println(PSS);
            if (PSS != null) {
        %>
        <h3><%=PSS.toString()%></h3>
        <%
            }
        %>

        <div class="container mt-4">
            <div class="row">
                <c:forEach var="p" items="${productos}">
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header">
                                <label>${p.getNombres()}</label>

                            </div>
                            <div class="card-body">
                                <i>$.${p.getPrecio()}</i>
                                <img src="${p.getFoto()}" width="200" height="180">
                            </div>
                            <div class="card-footer test-center">
                                <label>${p.getDescripcion()}</label>
                                <div>
                                    <a href="Controlador?accion=AgregarCarrito&id=${p.getId()}" class="btn btn-outline-info">Agregar a carrito</a>
                                    <a href="Controlador?accion=comprar&id=${p.getId()}" class="btn btn-danger">Comprar</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>

            </div>

        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
