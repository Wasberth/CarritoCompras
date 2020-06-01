package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelado.Operaciones;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Ticket;
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
    Ticket ticket = new Ticket();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

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

            case "ActualizarCantidad":
                //NO SIRVE XD
                int idpro = Integer.parseInt(request.getParameter("id"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < listacarrito.size(); i++) {
                    if (listacarrito.get(i).getIdProducto() == idpro) {
                        listacarrito.get(i).setCantidad(cant);
                        double st = listacarrito.get(i).getPrecioCompra() * cant;
                        listacarrito.get(i).setSubTotal(st);
                    }

                }
                break;

            case "generarCompra":
                String cliente = session.getAttribute("user").toString();
                List<String> prods = new ArrayList();
                List<Double> costs = new ArrayList();
                List<Integer> cantids = new ArrayList();
                List<Integer> ids = new ArrayList();
                double subtotal = 0,
                 total;
                int venta = new Operaciones().getLastSell() + 1;
                for (carrito listacarrito1 : listacarrito) {
                    prods.add(listacarrito1.getNombres());
                    costs.add(listacarrito1.getPrecioCompra());
                    cantids.add(listacarrito1.getCantidad());
                    ids.add(listacarrito1.getIdProducto());
                    subtotal = subtotal + (listacarrito1.getPrecioCompra() * listacarrito1.getCantidad());
                }
                total = subtotal * (1.16);
                try {
                    ticket = new Ticket(cliente, prods, costs, cantids, subtotal, total, venta, ids);
                } catch (Ticket.NoSuitableListSize ex) {
                    System.err.println(ex);
                }
                System.out.println(ticket.getTicketString());
                request.setAttribute("ticket", ticket.getTicketString());
                request.getRequestDispatcher("Ticket.jsp").forward(request, response);
                System.out.println("guardado");
                break;

            case "HacerCompra":
                System.out.println(ticket);
                String resultado = new Operaciones().comprar(ticket);
                if (resultado.equals("Success")) {
                    listacarrito.clear();
                    response.sendRedirect("Controlador?accion=home");
                } else {
                    List<carrito> nuevalistacarrito = new ArrayList<>();
                    List<Integer> noStock = Operaciones.getNOSTOCK();
                    System.out.println(noStock);
                    listacarrito.stream().filter((listacarrito1) -> (noStock.contains(listacarrito1.getIdProducto()))).forEachOrdered((listacarrito1) -> {
                        System.out.println(listacarrito1.getIdProducto());
                        nuevalistacarrito.add(listacarrito1);
                    });
                    System.out.println(listacarrito);
                    System.out.println(nuevalistacarrito);
                    listacarrito = nuevalistacarrito;
                    request.setAttribute("ProductosSinStock", resultado);
                    request.getRequestDispatcher("Controlador?accion=Carrito").forward(request, response);
                }
//                                request.getRequestDispatcher("indexUser.jsp").forward(request, response);
                break;
            default:
                System.out.println("default");
                System.out.println(productos);
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
