<%-- 
    Document   : Ticket
    Created on : 31/05/2020, 11:10:57 PM
    Author     : david
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jspdf.min.js" type="text/javascript"></script>
        <script>
            var doc = new jsPDF();
            console.log(<%=request.getParameter("ticket")%>);
            doc.text(20, 20, `<%=request.getAttribute("ticket")%>`);

            doc.setProperties({
                title: 'Ticket',
                subject: 'Ticket de compra de COVID-SHOP',
                author: 'Wilberth e Ismael',
                keywords: 'generated, javascript, web 2.0, ajax',
                creator: 'Wilberth e Ismael'
            });
            doc.save('Ticket.pdf');
            location.href = "Controlador?accion=HacerCompra";
        </script>
    </head>
    <body>
        <noscript><h1>Porfavor active los scripts para descargar el pdf</h1></noscript>
    </body>
</html>
