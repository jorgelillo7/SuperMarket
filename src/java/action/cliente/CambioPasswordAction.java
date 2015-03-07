/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

/**
 * Action para cambiar password del cliente
 *
 * @author Jorge
 */
import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import java.util.Map;
import modelos.Cliente;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class CambioPasswordAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a Datos
    ClienteDAO clienteDAO;
    private String mensaje;
    private String oldPass, newPass1, newPass2, id;
    private String error = null;
    private String exito = null;

    public String execute() {

        session = getSession();

        String username = ((Cliente) session.get("cliente")).getUsername();
        ClienteDAO cliDAO = new ClienteDAO();

        try {
            boolean check = cliDAO.checkPass(username, oldPass);

            if (check) {
                if (newPass1.equals(newPass2)) {

                    Cliente cli = cliDAO.findCliente(username);
                    cli.setPass(newPass1);
                    boolean update = cliDAO.updatePass(cli);
                    if (update) {
                        exito = "Contraseña actualizada favorablemente";
                        return "SUCCESS";
                    } else {
                        error = "Error modificando la contraseña";
                        return "ERROR";
                    }



                } else {
                    error = "No coinciden los campos nueva contraseña";
                    return "ERROR";
                }
            } else {  //check false
                error = "Revise la contraseña antigua";
                return "ERROR";
            }


        } catch (Exception e) {
            System.out.println(e);

        }

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

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass1() {
        return newPass1;
    }

    public void setNewPass1(String newPass1) {
        this.newPass1 = newPass1;
    }

    public String getNewPass2() {
        return newPass2;
    }

    public void setNewPass2(String newPass2) {
        this.newPass2 = newPass2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CambioPasswordAction() {

        clienteDAO = new ClienteDAO();
    }
}