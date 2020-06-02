<%-- 
    Document   : carrito
    Created on : 22/04/2020, 04:07:44 PM
    Author     : PORTO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/ali.css">
        <link href="css/estilos.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito Page</title>
    </head>
    <body>
        <noscript>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=Controlador?accion=home&mensaje=ActivaJavascript">
        </noscript>
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
                        <a class="nav-link" href="Controlador?accion=home"><i class="fas fa-cart-plus">Seguir comprando</a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="Logout.jsp"><i class="fas fa-cart-plus">Cerrar Sesión</a>
                </li>
                </ul>
                
                
            </div>
        </nav>

        <div class="container mt-4" style="margin-bottom: 2rem">
            <h3>Carrito</h3>
            <br>
            <div class="col-sm-12">
                <%
                    Object PSS = request.getAttribute("ProductosSinStock");
                    if (PSS != null) {
                %>
                <h3><%=PSS.toString()%></h3>
                <%
                    }
                %>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>NOMBRES</th>
                            <th>DESCRIPCION</th>
                            <th>PRECIO</th>
                            <th>CANT</th>
                            <th>SUBTOTAL</th>
                            <th>ELIMINAR</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="car" items="${carrito}">
                            <tr>
                                <td>${car.getNombres()}</td>
                                <td>${car.getDescripcion()}</td>
                                <td>${car.getPrecioCompra()}</td>
                                
                                

                                <td>
                                    <form class="form-inline" action="Controlador">
                                        <input type="hidden" id="accion" value="CambiarCantidad" name="accion">
                                        <input type="hidden" id="id" value="${car.getIdProducto()}" name="id">
                                            <div class="col-9">
                                                <input type="number" id="Cantidad" value="${car.getCantidad()}" class="form-control text-center" min="1" max="100000" name="cantidad">
                                            </div>
                                            <div class="col-2">
                                                <input type="submit" class="form-control text-center btn btn-danger" value="Actualizar">
                                            </div>
                                    </form>
                                </td>
                                <td>${car.getSubTotal()}</td>
                                <td>
<!--                                    <input type="hidden" id="idpro" type="text" value="${car.getIdProducto()}"></input>-->
                                    <a href="#" id="btnDelete" onclick="return eliminar(${car.getIdProducto()})">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-header">
                        <h3>Generar Compra</h3>
                    </div>
                    <div class="card-body">
                        <label>Subtotal:</label>
                        <input type="text" value="$.${totalPagar}" readonly="" class="form-control">
                        <label>Descuento:</label>
                        <input type="text" value="$0.00" readonly="" class="form-control">
                        <label>Total:</label>
                        <input type="text" value="${totalPagar}" readonly="" class="form-control">
                    </div>
                    <div class="card-footer">
                        <!--                        <a href="$" class="btn btn-info btn-block">Realizar Pago</a>-->
                        <a href="Controlador?accion=generarCompra" class="btn btn-danger">Generar Compra</a>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="js/funciones.js" type="text/javascript"></script>
</body>
</html>
