<%-- 
    Document   : registrar
    Created on : 1/06/2020, 09:20:26 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a81368914c.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="img">
                <img src="img/bg.svg">
            </div>
            <div class="login-content">
                <form action="Guardar.jsp" method="get">
                    <img src="img/avatar.svg">
                    <h2 class="title">Registrar</h2>
                    <h5>Al ingresar a nuestro sitio acepta nuestros <a href="#">términos y condiciones</a></h5>
                    <div class="input-div one">
                        <div class="i">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="div">
                            <h5>Usuario</h5>
                            <input type="text" class="input" required name="user">


                        </div>
                    </div>
                    <div class="input-div pass">
                        <div class="i"> 
                            <i class="fas fa-lock"></i>
                        </div>
                        <div class="div">
                            <h5>Password</h5>
                            <input type="password" class="input" required name="password"  size="45">
                        </div>
                    </div>
                    
                    <%
                    String nombre=request.getParameter("user");
                    String password=request.getParameter("password");
                    System.out.println("Registrar.jsp\n"
                            + "nombre: "+nombre+"\n"
                                    + "password: "+password);
                    %>

                    <a href="index.jsp">¿Ya tienes una cuenta? Inicia sesión</a>

                    <input type="submit" class="btn btn-outline-success" value="Registrar Cuenta">

                </form>
            </div>
        </div>
        <script type="text/javascript" src="js/main.js"></script>
    </body>
</html>
