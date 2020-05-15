package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.carrito;

/**
 *
 * @author PORTO
 */
public class Controlador extends HttpServlet {

    ProductoDAO pdao = new ProductoDAO();
    Producto p = new Producto();
    List<Producto> productos = new ArrayList<>(); 
    List<carrito> listacarrito = new ArrayList<>();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        productos = pdao.listar();
        int idp;
        carrito car;
        switch(accion){
            case "AgregarCarrito":
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                item = item+1;
                car = new carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad*p.getPrecio());
                listacarrito.add(car);
                request.setAttribute("contador", listacarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                
                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listacarrito);
                for(int i = 0; i<listacarrito.size();i++){
                    totalPagar = totalPagar+listacarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
                
            case "comprar":
                totalPagar=0.0;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                item = item+1;
                car = new carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad*p.getPrecio());
                listacarrito.add(car);
                for(int i = 0; i<listacarrito.size();i++){
                    totalPagar = totalPagar+listacarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listacarrito);
                request.setAttribute("contador", listacarrito.size());
                
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
                
            default:
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("indexUser.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
