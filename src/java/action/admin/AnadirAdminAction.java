/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.ClienteDAO;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para añadir nuevos administradores a la app
 *
 * @author Jorge
 */
public class AnadirAdminAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a Datos
    private String mensaje;
    private String username, email, password, repeatpassword;
    private String error = null;
    private String exito = null;

    public AnadirAdminAction() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatpassword() {
        return repeatpassword;
    }

    public void setRepeatpassword(String repeatpassword) {
        this.repeatpassword = repeatpassword;
    }

    public String execute() {

        if (!password.equals(repeatpassword)) {
            error = "Revise la contraseña, no coinciden los campos";
            return "ERROR";
        } else {
            Cliente admin = new Cliente();

            admin.setUsername(username);
            admin.setEmail(email);
            admin.setPass(password);

            admin.setPrivilegios(1);
            admin.setBaneado(0);

            ClienteDAO cliDAO = new ClienteDAO();
            try {
                cliDAO.nuevoCliente(admin, null);
                exito = "Administrador dado de alta";
                return "SUCCESS";
            } catch (Exception e) {
                System.out.println(e);
                return "ERROR";

            }

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