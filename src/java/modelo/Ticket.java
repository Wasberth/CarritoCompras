/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author david
 */
public class Ticket {

    public static class NoSuitableListSize extends Exception {

        public NoSuitableListSize() {
        }

        @Override
        public String toString() {
            return "El tamaño de las listas productos, costos y cantidades debe de ser la misma";
        }
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    private final String fecha;

    private String cliente;
    private List<String> productos;
    private List<Double> costos;
    private List<Integer> cantidades;
    private double subtotal;
    private double total;
    private int venta;

    public Ticket() {
        this.fecha = formatter.format(date);
    }

    public Ticket(String cliente, List<String> productos, List<Double> costos, List<Integer> cantidades, double subtotal, double total, int venta) throws NoSuitableListSize {
        if (productos.size() != costos.size() || productos.size() != cantidades.size() || costos.size() != cantidades.size()) {
            throw new NoSuitableListSize();
        }
        this.fecha = formatter.format(date);
        this.cliente = cliente;
        this.productos = productos;
        this.costos = costos;
        this.cantidades = cantidades;
        this.subtotal = subtotal;
        this.total = total;
        this.venta = venta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    public List<Double> getCostos() {
        return costos;
    }

    public void setCostos(List<Double> costos) {
        this.costos = costos;
    }

    public List<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public String getTicketString() {
        String ticketString = ""
                + "COVID-SHOP MÉXICO\n"
                + "COMPRA EN LÍNEA\n"
                + "=============================\n"
                + fecha + " VENTA " + venta + "\n";

        for (int i = 0; i < productos.size(); i++) {
            String producto = productos.get(i);
            double costo = costos.get(i);
            int cantidad = cantidades.get(i);
            ticketString = ticketString + producto + " x" + cantidad + " $" + costo + "\n";
        }

        ticketString = ticketString + ""
                + "-----------------------------\n"
                + "SUBTOTAL $" + subtotal + "\n"
                + "+ IVA 16%" + "\n"
                + "TOTAL $"+total + "\n"
                + "=============================\n"
                + "VERSIÓN COVID-SHOPv1.0\n"
                + "MUCHAS GRACIAS POR SU VISITA";

        return ticketString;
    }

}