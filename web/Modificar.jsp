<%-- 
    Document   : indexAdmin
    Created on : 21/04/2020, 09:56:09 PM
    Author     : PORTO
--%>


<%@page import="modelado.ModificaAgenda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "modelo.Contacto"%> 
<%@ page import = "modelo.ConsultaAgenda"%> 
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
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                </form>
                <li class="nav-item">
                    <a class="nav-link" href="Logout.jsp"><i class="fas fa-cart-plus">Cerrar Sesión</a>
                </li>
            </div>
        </nav>
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
  <a class="btn btn-outline-success" href="indexAdmin.jsp">Volver a Menú</a><br>
                <a class="btn btn-outline-success" href="CRUDArticulos.jsp">Modificar otro artículo</a>
        

    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="js/funciones.js" type="text/javascript"></script>
</body>
</html>
