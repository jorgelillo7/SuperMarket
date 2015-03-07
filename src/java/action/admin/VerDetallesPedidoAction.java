/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import daos.PedidoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Direccion;
import modelos.DireccionPedidos;
import modelos.LineaPedido;
import modelos.Pedido;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para ver detalles de
 *
 * @author Jorge
 */
public class VerDetallesPedidoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;
    private String id;
    private Pedido pedido;
    private String alerta;

    public VerDetallesPedidoAction() {
        pedidoDAO = new PedidoDAO();
        clienteDAO = new ClienteDAO();
    }

    @Override
    public String execute() {
        try {
            //Buscar pedido con id
            pedido = pedidoDAO.findPedido(id);
        } catch (SQLException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request = getRequest();


        ArrayList<LineaPedido> listado = new ArrayList<LineaPedido>();
        try {
            listado = pedidoDAO.findLineasFromIdPed(id);
        } catch (SQLException ex) {
            Logger.getLogger(action.cliente.VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(action.cliente.VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        pedido.setCarrito(listado);


        request.put("pedido", pedido);


        session = getSession();

        DireccionPedidos dir = new DireccionPedidos();
        try {
            dir = pedidoDAO.findDireccionActualizada(pedido.getIdPedido());
        } catch (SQLException ex) {
            Logger.getLogger(action.cliente.VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(action.cliente.VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
        }


        //si no hay dirección buscamos la dirección base del cliente
        if (dir == null) {
            Direccion direccion = new Direccion();
            try {
                direccion = clienteDAO.findDireccion(pedido.getIdUser());
            } catch (SQLException ex) {
                Logger.getLogger(action.cliente.VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(action.cliente.VerDetallesPedidoAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.put("direccionEnvio", direccion);
        } else {
            request.put("direccionEnvio", dir);
        }


        return "SUCCESS";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
