/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.LineaPedido;
import modelos.Pedido;
import modelos.Producto;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para modificar la cantidad de los elementos de la cesta
 *
 * @author Jorge
 */
public class ActualizarCantidadProductoCestaAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    ProductoDAO productoDAO;
    String id;
    String cantidad;
    String alerta = null;

    public ActualizarCantidadProductoCestaAction() {
        productoDAO = new ProductoDAO();
    }

    public String execute() {

        session = getSession();

        Producto producto = new Producto();
        try {
            producto = productoDAO.findProducto(id);
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarCantidadProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActualizarCantidadProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        Pedido pedido = (Pedido) session.get("cesta");
        List<LineaPedido> carrito = pedido.getCarrito();

        //Actualizamos el elemento linea de pedido asociado a nuestro id
        for (LineaPedido lp : carrito) {
            if (lp.getProd().getId().equals(id)) {
                lp.setCantidad(Integer.parseInt(cantidad));
                lp.setPrecioproducto(lp.getProd().getPrecio());
                lp.setTotal(Integer.parseInt(cantidad) * lp.getProd().getPrecio());
                break;
            }
        }

        float total = 0;
        for (LineaPedido lp : pedido.getCarrito()) {
            total += lp.getTotal();

        }
        pedido.setPreciototal(total);

        session.put("cesta", pedido);

        alerta = "Se ha actualizado a " + cantidad + " el producto " + producto.getNombre();
        return "SUCCESS";
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }
}