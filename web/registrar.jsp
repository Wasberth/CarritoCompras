<%-- 
    Document   : registrar
    Created on : 21/04/2020, 06:56:42 PM
    Author     : PORTO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a81368914c.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <div class="container">
            <div class="img">
                <img src="img/bg.svg">
            </div>
            <div class="login-content">
                <form action="Guardar.jsp" method="post">
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
                            <input type="password" class="input" required name="password">
                        </div>
                    </div>
                    <a href="index.jsp">¿Ya tienes una cuenta? Inicia sesión</a>
                    <input type="submit" class="btn" value="Registrar Cuenta">
                </form>
            </div>
        </div>
        <script type="text/javascript" src="js/main.js"></script>

    </body>
</html>
