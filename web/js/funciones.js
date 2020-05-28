//$(document).ready(function () {
//    $("tr #btnDelete").click(function () {
//        var id = $(this).parent().find("#idp").val();
//        console.log($(this).parent().find("#idp").values());
//        swal({
//            title: "Are you sure?",
//            text: "Once deleted, you will not be able to recover this imaginary file!",
//            icon: "warning",
//            buttons: true,
//            dangerMode: true,
//        })
//                .then((willDelete) => {
//                    if (willDelete) {
//                        eliminar(id);
//                        swal("Poof! Your imaginary file has been deleted!", {
//                            icon: "success",
//                        }).then((willDelete) => {
//                            if (willDelete) {
//                                parent.location.href = "Controlador?accion=carrito";
//                            }
//                        });
//                    } else {
//                        swal("Your imaginary file is safe!");
//                    }
//                });
//
//    });
//
//    $("tr #Cantidad").click(function () {
//        var id = $(this).parent().find("$idpro").val();
//        var cantidad = $(this).parent().find("#Cantidad").val();
//        var url = "Controlador?accion=ActualizarCantidad";
//        $.ajax({
//            type: 'POST',
//            url: url,
//            data
//        })
//    })
//});



function eliminar(id) {
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this imaginary file!",
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

                    alert("Producto eliminado exitosamente");
                }
            });
            swal("Poof! Your imaginary file has been deleted!", {
                icon: "success",
            });
        } else {
            swal("Your imaginary file is safe!");
        }
        
        $("tr #Cantidad").click(function (){
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
        });
        
    });

}