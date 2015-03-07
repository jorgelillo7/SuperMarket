/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
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
 * Action para ver los pedidos
 *
 * @author Jorge
 */
public class VerPedidosAdminAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;

    public VerPedidosAdminAction() {
    }

    @Override
    public String execute() {
        PedidoDAO pedidoDAO = new PedidoDAO();

        ArrayList<Pedido> basededatos = new ArrayList<Pedido>();
        try {
            basededatos = (ArrayList<Pedido>) pedidoDAO.findAllPedido();
        } catch (SQLException ex) {
            Logger.getLogger(VerPedidosAdminAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerPedidosAdminAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        //guardamos en la respuesta la lista de pedidos
        request.put("basedatos", basededatos);

        return "SUCCESS";
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