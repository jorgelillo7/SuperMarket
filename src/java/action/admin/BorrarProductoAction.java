/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.util.Map;
import modelos.Producto;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action Eliminar producto
 *
 * @author Jorge
 */
public class BorrarProductoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scope
    private Map session;
    private Map<String, Object> request;
    //Acceso a Datos
    private String mensaje;
    private String id;
    private String error = null;
    private String exito = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BorrarProductoAction() {
    }

    public String execute() {

        ProductoDAO proDAO = new ProductoDAO();
        try {
            Producto pro = proDAO.findProducto(id);


            boolean check = proDAO.deleteProduct(pro);
            if (check) {
                exito = "Producto eliminado de forma correcta";
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
