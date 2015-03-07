/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.util.ArrayList;
import java.util.Map;
import modelos.Producto;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para ver el listado de productos
 *
 * @author Jorge
 */
public class VerProductosAdminAction extends ActionSupport implements RequestAware, SessionAware {

    //Scope
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    private String mensaje;

    public VerProductosAdminAction() {
    }

    public String execute() throws Exception {

        ProductoDAO proDAO = new ProductoDAO();
        ArrayList<Producto> listaProductos;
        try {
            listaProductos = (ArrayList<Producto>) proDAO.findAllProducts();
            request.put("listaProductos", listaProductos);
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
