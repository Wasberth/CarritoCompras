package modelado;
import java.util.List;
import modelo.carrito;
/**
 *
 * @author PORTO
 */
public class Compra {

    private int id;
    private Cliente cliente;
    private int idpago;
    private String fecha;
    private int cantidad;
    private Double monto;
    
    private List<carrito>detalleCompras;
    
    public Compra(){
        
    }

    public Compra(int id, Cliente cliente, int idpago, String fecha, Double monto, List<carrito> detalleCompras, int cantidad) {
        this.id = id;
        this.cliente = cliente;
        this.idpago = idpago;
        this.fecha = fecha;
        this.monto = monto;
        this.cantidad = cantidad;
        this.detalleCompras = detalleCompras;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public List<carrito> getDetalleCompras() {
        return detalleCompras;
    }

    public void setDetalleCompras(List<carrito> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
     
}
