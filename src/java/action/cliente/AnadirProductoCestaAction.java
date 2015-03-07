/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.PedidoDAO;
import daos.ProductoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Action para incluir un producto a la cesta de compra
 *
 * @author Jorge
 */
public class AnadirProductoCestaAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    // Acceso a datos
    PedidoDAO pedidoDAO;
    ProductoDAO productoDAO;
    private String id, cantidad;
    private String alerta;

    public AnadirProductoCestaAction() {
    }

    public String execute() {
        session = getSession();
        pedidoDAO = new PedidoDAO();
        productoDAO = new ProductoDAO();

        ArrayList<Pedido> lista = new ArrayList<Pedido>();

        Pedido pedido;
        if (session.containsKey("cesta")) {
            pedido = (Pedido) session.get("cesta");
            //si ya hay una cesta en la sesion actual la obtenemos por medio de la sesión y trabajamos sobre ella, 
            //añadiendo más cantidad a los productos que ya hayan sido introducidos
            List<LineaPedido> carrito = pedido.getCarrito();

            boolean encontrado = false;
            for (LineaPedido lp : carrito) {


                int idProducto = lp.getIdProducto();
                Producto prodActual = new Producto();
                try {
                    prodActual = productoDAO.findProducto(String.valueOf(idProducto));
                } catch (SQLException ex) {
                    Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
                }


                if (prodActual.getId().equals(id)) {
                    lp.setCantidad(lp.getCantidad() + Integer.parseInt(cantidad));
                    lp.setTotal(lp.getCantidad() * prodActual.getPrecio());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                //si no esta en la cesta lo añadimos como nuevo 
                Producto producto = new Producto();
                try {
                    producto = productoDAO.findProducto(id);
                } catch (SQLException ex) {
                    Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
                }
                LineaPedido lineapedido = new LineaPedido();
                lineapedido.setCantidad(Integer.valueOf(cantidad));
                lineapedido.setProd(producto);
                lineapedido.setIdProducto(Integer.parseInt(id));
                lineapedido.setPrecioproducto(producto.getPrecio());
                lineapedido.setTotal(Integer.valueOf(cantidad) * producto.getPrecio());
                pedido.getCarrito().add(lineapedido);
            }

            float total = 0;
            for (LineaPedido lp : pedido.getCarrito()) {
                total += lp.getTotal();

            }
            int totalPedidos = 0;
            pedido.setPreciototal(total);
            try {
                totalPedidos = pedidoDAO.countPedidos();
            } catch (SQLException ex) {
                Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            totalPedidos += 1;
            pedido.setIdPedido(totalPedidos);

            session.put("cesta", pedido);
        } else {
            //Si no hay cesta la creamos 
            pedido = new Pedido();

            Producto producto = new Producto();
            try {
                producto = productoDAO.findProducto(id);
            } catch (SQLException ex) {
                Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            LineaPedido lineapedido = new LineaPedido();
            lineapedido.setCantidad(Integer.valueOf(cantidad));
            lineapedido.setProd(producto);
            lineapedido.setIdProducto(Integer.parseInt(id));
            lineapedido.setPrecioproducto(producto.getPrecio());
            lineapedido.setTotal(Integer.valueOf(cantidad) * producto.getPrecio());
            pedido.getCarrito().add(lineapedido);


            float total = 0;
            for (LineaPedido lp : pedido.getCarrito()) {
                total += lp.getTotal();

            }
            int totalPedidos2 = 0;
            pedido.setPreciototal(total);
            try {
                totalPedidos2 = pedidoDAO.countPedidos();
            } catch (SQLException ex) {
                Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AnadirProductoCestaAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            totalPedidos2 += 1;
            pedido.setIdPedido(totalPedidos2);
            session.put("cesta", pedido);
        }

        alerta = "Se ha añadido un producto a su cesta";
        return "SUCCESS";

    }

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public String getId() {
        return id;
    }
    public String getCantidad() {
        return cantidad;
    }

    public void setId(String id) {
        this.id = id;
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

    public Map getRequest() {
        return request;
    }
}
