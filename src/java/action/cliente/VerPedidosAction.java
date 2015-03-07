/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import daos.PedidoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Pedido;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para ver los pedidos comprado por un cliente
 *
 * @author Jorge
 */
public class VerPedidosAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    PedidoDAO pedidoDAO;
    ClienteDAO clienteDAO;
    private String mensaje;
    private String id;

    public VerPedidosAction() {
        pedidoDAO = new PedidoDAO();
        clienteDAO = new ClienteDAO();
    }

    public String execute() {
        ArrayList<Pedido> listado = new ArrayList<Pedido>();
        try {
            listado = pedidoDAO.findOrdersByUser(id);
        } catch (SQLException ex) {
            Logger.getLogger(VerPedidosAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerPedidosAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.put("listado", listado);

        return "SUCCESS";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}