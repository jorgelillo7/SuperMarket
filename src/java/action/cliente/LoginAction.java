/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

/**
 * Action para realizar login en la aplicación
 *
 * @author Jorge
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import daos.ClienteDAO;
import modelos.Cliente;
import modelos.Direccion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware {

    //Scope
    private Map session;
    //Acceso a Datos
    private String errormessage;
    private String username;
    private String password;
    private String error = null;

    @Override
    @Action(value = "CheckLogin", results = {
        @Result(name = "SUCCESS", location = "/index.jsp"),
        @Result(name = "ERROR", location = "/index.jsp")
    })
    public String execute() {
        session = ActionContext.getContext().getSession();  // Get session


        ClienteDAO cliDAO = new ClienteDAO();
        try {
            if (getUsername() == null || getUsername().equals("")) {
                error = "Introduzca un usuario";
                return "ERROR";
            } else {
                boolean login = cliDAO.loginApp(getUsername(), getPassword());

                if (login) {
                    // session.put("loggedin","true");
                    Cliente cliente = cliDAO.findCliente(getUsername());
                    if (cliente.getBaneado() == 1) {  //comprobación baneo
                        error = "Este usuario ha sido baneado";
                        return "ERROR";
                    }
                    session.put("cliente", cliente); //guardamos en la sesión el cliente y su dirección asociada
                    Direccion direccion = cliDAO.findDireccion(getUsername());
                    session.put("direccion", direccion);
                    session.put("logged", true);
                    return "SUCCESS";
                } else {

                    error = "Error iniciando sesión, compruebe sus datos";
                    return "ERROR";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = "ERROR_INTERNO";

        }
        return "SUCCESS";

    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}