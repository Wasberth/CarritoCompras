package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
        System.out.println(accion);
        switch (accion) {
            case "AgregarCarrito":
                int pos = 0;
                cantidad = 1;

                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                if (listacarrito.size() > 0) {
                    for (int i = 0; i < listacarrito.size(); i++) {
                        if (idp == listacarrito.get(i).getIdProducto()) {
                            pos = i;
                        }
                        if (idp == listacarrito.get(pos).getIdProducto()) {
                            cantidad = listacarrito.get(pos).getCantidad() + cantidad;
                            double subtotal = listacarrito.get(pos).getPrecioCompra() * cantidad;
                            listacarrito.get(pos).setCantidad(cantidad);
                            listacarrito.get(pos).setSubTotal(subtotal);

                        } else {
                            System.out.println("Wha?");
                            item = item + 1;
                            car = new carrito();
                            car.setItem(item);
                            car.setIdProducto(p.getId());
                            car.setNombres(p.getNombres());
                            car.setDescripcion(p.getDescripcion());
                            car.setPrecioCompra(p.getPrecio());
                            car.setCantidad(cantidad);
                            car.setSubTotal(cantidad * p.getPrecio());

                            listacarrito.add(car);
                            System.out.println(listacarrito);
                        }
                    }

                } else {
                    item = item + 1;
                    car = new carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * p.getPrecio());

                    listacarrito.add(car);
                }

                request.setAttribute("contador", listacarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);

                break;
            case "Carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listacarrito);
                for (int i = 0; i < listacarrito.size(); i++) {
                    totalPagar = totalPagar + listacarrito.get(i).
                            getSubTotal();
                    System.out.println("Total a pagar carrito\n" + totalPagar);
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;

            case "comprar":
                totalPagar = 0.0;
                idp = Integer.parseInt(request.getParameter("id"));
                p = pdao.listarId(idp);
                item = item + 1;
                car = new carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad * p.getPrecio());
                listacarrito.add(car);
                for (int i = 0; i < listacarrito.size(); i++) {
                    totalPagar = totalPagar + listacarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listacarrito);
                request.setAttribute("contador", listacarrito.size());
                request.setAttribute("accion", "carrito");
                
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;

            case "Delete":
                int idproducto = Integer.parseInt(request.getParameter("id"));
                for (int i = 0; i < listacarrito.size(); i++) {
                    if (listacarrito.get(i).getIdProducto() == idproducto) {
                        listacarrito.remove(i);
                    }
                }
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
                
            default:
                System.out.println("default");
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
