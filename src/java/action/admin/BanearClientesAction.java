/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import java.util.Map;
import modelos.Cliente;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para banear usuarios
 *
 * @author Jorge
 */
public class BanearClientesAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a Datos
    private String mensaje;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BanearClientesAction() {
    }

    public String execute() {

        ClienteDAO cliDAO = new ClienteDAO();
        try {
            Cliente cliente = cliDAO.findCliente(username);

            if (cliente.getBaneado() == 0) {
                cliente.setBaneado(1);
            } else {
                cliente.setBaneado(0);
            }

            cliDAO.changeBaneo(cliente);
            return "SUCCESS";

        } catch (Exception e) {

            System.out.println(e);
            return "ERROR";

        }

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