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
            doc.text(<%=request.getParameter("ticket")%>, doc.internal.pageSize.width, 50, null, null, 'center');
            doc.save('Ticket.pdf');
        </script>
    </head>
    <body>
        <noscript><h1>Porfavor active los scripts para descargar el pdf</h1></noscript>
    </body>
</html>
