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
    int item = 0;
    double totalPagar = 0.0;
    int cantidad = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String accion = request.getParameter("accion");
        productos = pdao.listar();
        int idp;
        carrito car = new carrito();
        System.out.println(accion);
        switch (accion) {
            case "AgregarCarrito":
                try {
                    int pos = 0;
                    idp = Integer.parseInt(request.getParameter("id"));
                    p = pdao.listarId(idp);
                    agregarCarrito(idp, car);
                    request.setAttribute("contador", listacarrito.size());
                    request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                } catch (IOException | NumberFormatException | ServletException e) {
                    response.sendRedirect("Controlador?accion=home");
                }
                break;
            case "CambiarCantidad":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int can = Integer.parseInt(request.getParameter("cantidad"));
                    for (carrito listacarrito1 : listacarrito) {
                        if (listacarrito1.getIdProducto() == id) {
                            listacarrito1.setCantidad(can);
                            listacarrito1.setSubTotal(listacarrito1.getPrecioCompra() * can);
                        }
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("Controlador?accion=home");
                }
            //NO AGREGAR BREAK AQUÍ NI OTRO CASE
            case "Carrito":
                try {
                    totalPagar = 0.0;
                    request.setAttribute("carrito", listacarrito);
                    for (int i = 0; i < listacarrito.size(); i++) {
                        totalPagar = totalPagar + listacarrito.get(i).
                                getSubTotal();
                        System.out.println("Total a pagar carrito\n" + totalPagar);
                    }
                    request.setAttribute("totalPagar", totalPagar);
                    request.getRequestDispatcher("carrito.jsp").forward(request, response);
                } catch (IOException | ServletException e) {
                    response.sendRedirect("Controlador?accion=home");
                }
                break;
            case "comprar":
                try {
                    idp = Integer.parseInt(request.getParameter("id"));
                    p = pdao.listarId(idp);
                    agregarCarrito(idp, car);
                    request.setAttribute("totalPagar", totalPagar);
                    request.setAttribute("carrito", listacarrito);
                    request.setAttribute("contador", listacarrito.size());
                    request.setAttribute("accion", "carrito");
                    response.sendRedirect("Controlador?accion=Carrito");
                } catch (IOException | NumberFormatException e) {
                    response.sendRedirect("Controlador?accion=home");
                }
                break;

            case "Delete":
                try {
                    int idproducto = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < listacarrito.size(); i++) {
                        if (listacarrito.get(i).getIdProducto() == idproducto) {
                            listacarrito.remove(i);
                        }
                    }
                    request.getRequestDispatcher("carrito.jsp").forward(request, response);
                } catch (IOException | NumberFormatException | ServletException e) {
                    response.sendRedirect("Controlador?accion=home");
                }
                break;

            case "ActualizarCantidad":
                try {
                    int idpro = Integer.parseInt(request.getParameter("id"));
                    int cant = Integer.parseInt(request.getParameter("Cantidad"));
                    for (int i = 0; i < listacarrito.size(); i++) {
                        if (listacarrito.get(i).getIdProducto() == idpro) {
                            listacarrito.get(i).setCantidad(cant);
                            double st = listacarrito.get(i).getPrecioCompra() * cant;
                            listacarrito.get(i).setSubTotal(st);
                        }

                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("Controlador?accion=home");
                }
                break;

            case "generarCompra":
                try {
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
                } catch (Exception e) {
                    response.sendRedirect("Controlador?accion=home");
                }
                break;

            case "HacerCompra":
                try {
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
                } catch (Exception e) {
                    request.getRequestDispatcher("Controlador?accion=generarCompra").forward(request, response);
                }
                break;
            default:
                System.out.println("default");
                System.out.println(productos);
                request.setAttribute("productos", productos);
                request.setAttribute("contador", listacarrito.size());
                request.getRequestDispatcher("indexUser.jsp").forward(request, response);
        }
    }

    private void agregarCarrito(int idp, carrito car) {
        System.out.println("agregar carrito");
        if (listacarrito.size() < 0) {
            System.out.println("true1");
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
        } else {
            System.out.println("false1");
            boolean repetido = false;
            int repeticion = 0;
            for (int i = 0; i < listacarrito.size(); i++) {
                carrito listacarrito1 = listacarrito.get(i);
                if (listacarrito1.getIdProducto() == idp) {
                    repetido = true;
                    repeticion = i;
                    break;
                }
            }
            if (repetido) {
                System.out.println("true 2");
                listacarrito.get(repeticion).setCantidad(listacarrito.get(repeticion).getCantidad() + cantidad);
            } else {
                System.out.println("false2");
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
