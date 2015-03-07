/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.PedidoDAO;
import java.util.Map;
import modelos.Pedido;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action cambiar estado de los pedidos
 *
 * @author Jorge
 */
public class CambiarEstadoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scope
    private Map session;
    private Map<String, Object> request;
    //Acceso a Datos
    private String mensaje;
    private String estadonuevo;
    private String id;

    public CambiarEstadoAction() {
    }

    public String execute() throws Exception {
        PedidoDAO pedDAO = new PedidoDAO();
        try {
            Pedido ped = pedDAO.findPedido(id);
            ped.setEstado(Integer.parseInt(estadonuevo));

            boolean check = pedDAO.updatePedido(ped);

            if (check) {

                mensaje = "Estado actualizado";
                return "input";

            } else {
                mensaje = "fallo";
                return "ERROR";
            }


        } catch (Exception e) {

            System.out.println(e);
            return "ERROR";

        }

    }

    public String getEstadonuevo() {
        return estadonuevo;
    }

    public void setEstadonuevo(String estadonuevo) {
        this.estadonuevo = estadonuevo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
