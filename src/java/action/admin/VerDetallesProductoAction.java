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
 * Action para ver detalles de un producto
 *
 * @author Jorge
 */
public class VerDetallesProductoAction extends ActionSupport implements RequestAware, SessionAware {

    // Scopes
    private Map session;
    private Map<String, Object> request;
    // Acceso a datos
    private String mensaje;
    private String idPro;

    public VerDetallesProductoAction() {
    }

    public String getIdPro() {
        return idPro;
    }

    public void setIdPro(String idPro) {
        this.idPro = idPro;
    }

    public String execute() {
        ProductoDAO productoDAO = new ProductoDAO();
        try {
            Producto pro = productoDAO.findProducto(idPro);
            request.put("productoDetallado", pro);
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
