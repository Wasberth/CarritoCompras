$(document).ready(function () {
    $("tr #btnDelete").click(function () {
        var id = $(this).parent().find("#id").val();
        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this imaginary file!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        eliminar(id);
                        swal("Poof! Your imaginary file has been deleted!", {
                            icon: "success",
                        }).then((willDelete)=>{
                            if(willDelete){
                                parent.location.href="Controlador?accion=carrito";
                            }
                        });
                    } else {
                        swal("Your imaginary file is safe!");
                    }
                });

    });
    function eliminar(id) {
        var url = "Controlador?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "id= " + id,
            succes: function (data, textStatus, jqXHR) {
                alert("Producto eliminado exitosamente");
            }
        });
    }
    
    $("tr #Cantidad").click(function (){
        var id = $(this).parent().find("$idpro").val();
        var cantidad = $(this).parent().find("#Cantidad").val();
        var url = "Controlador?accion=ActualizarCantidad";
        $.ajax({
            type: 'POST',
            url: url,
            data
        })
    })
});