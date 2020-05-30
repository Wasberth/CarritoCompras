function eliminar(id) {
    swal({
        title: "Estas seguro?",
        text: "Se eliminará el artículo del carrito",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((willDelete) => {
        if (willDelete) {
            var url = "Controlador?accion=Delete";
            $.ajax({
                type: 'POST',
                url: url,
                data: {id: id},
                dataType: 'json',
                success: function (data, textStatus, jqXHR) {
                    location.href="Controlador?accion=Carrito";

                }
            });
            
            swal("Se ha eliminado correctamente el artículo del carrito", {
                icon: "success",

            });
            location.reload();
        } else {
            swal("No se elimino ningún articulo");
        }
        
        
        
        
    });

}

function actualizar(){
            var idp = $(this).parent().find("#id").val();
            var cantidad = $(this).parent().find("#Cantidad").val();
            var url = "Controlador?accion = ActualizarCantidad";
            $.ajax({
               type:'POST',
               url:url,
               data: "idp="+idp+"&Cantidad="+cantidad,
               success: function(data,textStatus,jqXHR){
                   location.href="Controlador?accion=Carrito";
               }
                
            });
        }
        