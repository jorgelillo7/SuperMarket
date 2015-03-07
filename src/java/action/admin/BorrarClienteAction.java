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
 * Action Borrar cliente
 *
 * @author Jorge
 */
public class BorrarClienteAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a Datos
    private String mensaje;
    private String username;
    private String error = null;
    private String exito = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BorrarClienteAction() {
    }

    public String execute() {

        ClienteDAO cliDAO = new ClienteDAO();
        try {
            Cliente cliente = cliDAO.findCliente(username);

            boolean check = cliDAO.deleteUser(cliente);
            if (check) {
                exito = "Usuario eliminado de forma correcta";
                return "SUCCESS";
            } else {
                error = "Problema inesperado al borrar";
                return "ERROR";
            }

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExito() {
        return exito;
    }

    public void setExito(String exito) {
        this.exito = exito;
    }
}
