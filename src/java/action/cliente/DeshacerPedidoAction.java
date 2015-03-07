/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

import com.opensymphony.xwork2.ActionSupport;
import daos.ProductoDAO;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Action para cancelar el pedido y eliminar la cesta de la sesion activa
 *
 * @author Jorge
 */
public class DeshacerPedidoAction extends ActionSupport implements RequestAware, SessionAware {

    //Scopes
    private Map session;
    private Map<String, Object> request;
    //Acceso a datos
    ProductoDAO productoDAO;
    String alerta = null;

    public DeshacerPedidoAction() {
    }

    public String execute() {

        session = getSession();

        if (session != null & session.containsKey("cesta")) {
            session.remove("cesta");
            alerta = "Su cesta se ha vaciado";
        }

        return "SUCCESS";
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
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