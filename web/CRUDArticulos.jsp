<%-- 
    Document   : indexAdmin
    Created on : 21/04/2020, 09:56:09 PM
    Author     : PORTO
--%>


<%@page import="modelo.ConsultaAgenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "modelo.Producto"%> 
<%@ page import = "modelo.ProductoDAO"%> 
<%@ page import = "java.util.LinkedList"%> 
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/ali.css">
        <link href="css/estilos.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Constular perfiles Admin Page</title>
    </head>
    <body>
        <%
            try{
                HttpSession sesion = request.getSession();
                sesion.getAttribute("user");
                sesion.getAttribute("nivel");
                if(sesion.getAttribute("nivel")!="1"){
                    sesion.removeAttribute("user");
                    sesion.removeAttribute("nivel");
                    response.sendRedirect("ErrorPage.jsp");
                }else{
                String nom_user = sesion.getAttribute("user").toString();
                }
            }catch(Exception e){
                response.sendRedirect("ErrorPage.jsp");
            }

        %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">COVID-19 Shop Administration</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="indexAdmin.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Logout.jsp"><i class="fas fa-cart-plus">Cerrar Sesión</a>
                    </li>
                </ul>


            </div>
        </nav>

        <div class="col-sm-8" style="align-content: center;">
            <table class="table table-hover" >
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <%
                           
                            LinkedList<Producto> lista = ConsultaAgenda.getProductos();
                            for (int i = 0; i < lista.size(); i++) {
                        %>
                    <tr>
                        <td><input id="id <%=lista.get(i).getId()%>" disabled type="text"  value="<%=lista.get(i).getId()%>"></td>
                        <td><input id="nombre <%=lista.get(i).getId()%>" disabled type="text"   value="<%=lista.get(i).getNombres()%>"></td>
                        <td><input id="descripcion <%=lista.get(i).getId()%>" disabled type="text"  value="<%=lista.get(i).getDescripcion()%>"></td>
                        <td><input id="precio <%=lista.get(i).getId()%>" disabled type="text"   value="<%=lista.get(i).getPrecio()%>"></td>
                        <td><input id="stock <%=lista.get(i).getId()%>" disabled type="text"   value="<%=lista.get(i).getStock()%>"></td>
                        <td>
                            <form id="form <%=lista.get(i).getId()%>" action="Modificar.jsp">
                                <input id="input nombre <%=lista.get(i).getId()%>" name="nombre_producto" type="hidden">
                                <input id="input descripcion <%=lista.get(i).getId()%>" name="desc_prod" type="hidden">
                                <input id="input precio <%=lista.get(i).getId()%>" name="precio_prod" type="hidden">
                                <input id="input stock <%=lista.get(i).getId()%>" name="stock_prod" type="hidden">
                                <input id="input <%=lista.get(i).getId()%>" class="editar" type="button" value="Editar" onclick="(function () {
                                            var botones = document.getElementsByClassName('editar');
                                            for (var i = 0; i < botones.length; i++) {
                                                botones[i].hidden = true;
                                            }
                                            document.getElementById('nombre <%=lista.get(i).getId()%>').disabled = false;
                                            document.getElementById('descripcion <%=lista.get(i).getId()%>').disabled = false;
                                            document.getElementById('precio <%=lista.get(i).getId()%>').disabled = false;
                                            document.getElementById('stock <%=lista.get(i).getId()%>').disabled = false;
                                            document.getElementById('submit <%=lista.get(i).getId()%>').hidden = false;
                                            document.getElementById('cancel <%=lista.get(i).getId()%>').hidden = false;
                                            document.getElementById('input <%=lista.get(i).getId()%>').hidden = true;

                                        })()">
                                <input id="submit <%=lista.get(i).getId()%>" type="button" hidden value="Guardar cambios" onclick="(function () {
                                            document.getElementById('input nombre <%=lista.get(i).getId()%>').value = document.getElementById('nombre <%=lista.get(i).getId()%>').value;
                                            document.getElementById('input descripcion <%=lista.get(i).getId()%>').value = document.getElementById('descripcion <%=lista.get(i).getId()%>').value;
                                            document.getElementById('input precio <%=lista.get(i).getId()%>').value = document.getElementById('precio <%=lista.get(i).getId()%>').value;
                                            document.getElementById('input stock <%=lista.get(i).getId()%>').value = document.getElementById('stock <%=lista.get(i).getId()%>').value;
                                            document.getElementById('form <%=lista.get(i).getId()%>').submit();
                                        })()">
                                <input id="cancel <%=lista.get(i).getId()%>" type="button" hidden value="Cancelar" onclick="(function () {
                                            location.reload();
                                        })()">
                            </form>
                        </td>
                        <td>
                            <form action="Delete.jsp">
                                <input id="delete <%=lista.get(i).getId()%>" name="nombre_producto" type="hidden" value="<%=lista.get(i).getNombres()%>">
                                <input type="submit" value="Eliminar">
                            </form>
                        </td>
                    </tr>
                    <%
                        }

                    %>
                    </tr>

                </tbody>
            </table>
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

